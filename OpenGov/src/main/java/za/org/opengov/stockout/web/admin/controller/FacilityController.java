package za.org.opengov.stockout.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import za.org.opengov.common.entity.IssueState;
import za.org.opengov.common.entity.StaffMember;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.FacilityType;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutService;
import za.org.opengov.stockout.service.medical.ProductService;
import za.org.opengov.stockout.web.admin.domain.FacilityWrapper;
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;
import za.org.opengov.stockout.web.admin.domain.StaffMemberWrapper;
import za.org.opengov.stockout.web.admin.domain.StockoutWrapper;

@Controller
@RequestMapping(value = "sows/admin/facilities")
public class FacilityController extends AbstractPaginationController {

	@Autowired
	private FacilityService facilityService;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") long page,
			Model model) {

		long totalItems = facilityService.getCount();

		int noOfPages = (int) Math.ceil((double) totalItems
				/ (double) RESULTS_PER_PAGE);

		List<Facility> results = facilityService.getPage((int) page - 1,
				RESULTS_PER_PAGE);

		model.addAttribute("currentPage", page);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("results", results);

		return "admin/facilities/List";
	}
	
	@RequestMapping(value="new",method = RequestMethod.GET, produces = "text/html")
	public String NewPage(Model model){
		
		List<String> provinces = facilityService.listAllProvinces();
		List<String> districts = facilityService.listAllDistrictsForProvince(provinces.get(0));
		List<String> towns = facilityService.listAllTownsForDistrict(districts.get(0));

		FacilityType[] fTypes = FacilityType.values();
		
		model.addAttribute("types",fTypes);
		model.addAttribute("provinces", provinces);
		model.addAttribute("districts", districts);
		model.addAttribute("towns", towns);

		

		return("admin/facilities/New");
	}
	

	
	@ModelAttribute("facilitywrapper")
    private FacilityWrapper getFacilityWrapper() {
        return new FacilityWrapper();
    }
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute FacilityWrapper facilityWrapper,BindingResult result,Model model) {
		
		if (result.hasErrors()) {		
			
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
			System.out.println("ERROR");
			List<String> provinces = facilityService.listAllProvinces();

			List<String> districts = facilityService.listAllDistrictsForProvince(provinces.get(0));
			
			List<String> towns = facilityService.listAllTownsForDistrict(districts.get(0));
			
			FacilityType[] fTypes = FacilityType.values();
			
			model.addAttribute("types",fTypes);
			model.addAttribute("provinces", provinces);
			model.addAttribute("towns", towns);
			model.addAttribute("districts", districts);
			
			return"admin/facilities/New";
		}
		
		Facility facility = new Facility();
		facility.setDistrict(facilityWrapper.getDistrict());
		facility.setProvince(facilityWrapper.getProvince());
		facility.setTown(facilityWrapper.getTown());
		facility.setEmailAddress(facilityWrapper.getEmailAddress());
		facility.setLocalName(facilityWrapper.getLocalName());
		facility.setOfficialDOHName(facilityWrapper.getOfficialName());
		facility.setLatitudeDecimalDegress(facilityWrapper.getLatitude());
		facility.setLongitudeDecimalDegrees(facilityWrapper.getLongitude());
		facility.setUid(facilityWrapper.getLocalName().substring(0, 4).toUpperCase());
		facility.setFacilityType(facilityWrapper.getType());
		facility.setContactNumber(facilityWrapper.getContactNumber());
		
		facilityService.put(facility);
		
		return ("redirect:/sows/admin/facilities");
	
	}

	
	@RequestMapping(value = "{uid}", produces = "text/html")
	public String edit(@PathVariable("uid") String uid, Model model) {
		
		FacilityWrapper facilityWrapper = new FacilityWrapper(facilityService.get(uid));
		
		List<String> provinces = facilityService.listAllProvinces();
		List<String> districts = facilityService.listAllDistrictsForProvince(facilityWrapper.getProvince());
		List<String> towns = facilityService.listAllTownsForDistrict(facilityWrapper.getDistrict());
		
		
		
		FacilityType[] fTypes = FacilityType.values();
		
		model.addAttribute("facilitywrapper",facilityWrapper );
		model.addAttribute("types",fTypes);
		model.addAttribute("provinces", provinces);
		model.addAttribute("districts", districts);
		model.addAttribute("towns", towns);
		model.addAttribute("uid", uid);
		
		return "admin/facilities/Edit";
	}

	@RequestMapping(value = "{uid}/delete", produces = "text/html")
	public String delete(@PathVariable("uid") String uid, Model model) {
			
			facilityService.remove(facilityService.get(uid));
		return "redirect:/sows/admin/facilities";
	}

	@RequestMapping(value = "{uid}/update", method = RequestMethod.POST, produces = "text/html")
	public String update(@Valid @ModelAttribute FacilityWrapper facilityWrapper,BindingResult result, 
			@PathVariable("uid") String uid,Model model) {
		
		Facility facility = new Facility();
		facility.setDistrict(facilityWrapper.getDistrict());
		facility.setProvince(facilityWrapper.getProvince());
		facility.setTown(facilityWrapper.getTown());
		facility.setEmailAddress(facilityWrapper.getEmailAddress());
		facility.setLocalName(facilityWrapper.getLocalName());
		facility.setLatitudeDecimalDegress(facilityWrapper.getLatitude());
		facility.setLongitudeDecimalDegrees(facilityWrapper.getLongitude());
		facility.setOfficialDOHName(facilityWrapper.getOfficialName());
		facility.setUid(facilityWrapper.getLocalName().substring(0, 4).toUpperCase());
		facility.setFacilityType(facilityWrapper.getType());
		facility.setContactNumber(facilityWrapper.getContactNumber());

		facilityService.put(facility);
		
		return "redirect:/sows/admin/facilities";
	}
	

}
