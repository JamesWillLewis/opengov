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
		List<String> districts = new ArrayList<String>();
		List<String> towns = new ArrayList<String>();
		for(String prov : provinces){
			
			districts.addAll(facilityService.listAllDistrictsForProvince(prov));
			
		}
		
		for(String Dist : districts){
			towns.addAll(facilityService.listAllTownsForDistrict(Dist));
		}
		
		FacilityType[] fTypes = FacilityType.values();
		
		model.addAttribute("types",fTypes);
		model.addAttribute("towns", towns);
		
		

		return("admin/products/New");
	}
	

	
	@ModelAttribute("facilitywrapper")
    private FacilityWrapper getFacilityWrapper() {
        return new FacilityWrapper();
    }
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute FacilityWrapper facilityWrapper,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			
			List<String> provinces = facilityService.listAllProvinces();
			List<String> districts = new ArrayList<String>();
			List<String> towns = new ArrayList<String>();
			for(String prov : provinces){
				
				districts.addAll(facilityService.listAllDistrictsForProvince(prov));
				
			}
			
			for(String Dist : districts){
				towns.addAll(facilityService.listAllTownsForDistrict(Dist));
			}
			
			FacilityType[] fTypes = FacilityType.values();
			
			model.addAttribute("types",fTypes);
			model.addAttribute("towns", towns);
			
			return"admin/facilities/New";
		}
		
		Facility facility = new Facility();
		facility.setTown(facilityWrapper.getTown());
		facility.setEmailAddress(facility.getEmailAddress());
		facility.setLocalName(facilityWrapper.getLocalName());
		facility.setOfficialDOHName(facilityWrapper.getOfficialName());
		facility.setUid("uid");
		facility.setFacilityType(facilityWrapper.getType());
		facility.setContactNumber(facilityWrapper.getContactNumber());
		
		facilityService.put(facility);
		
		return ("redirect:/sows/admin/facilities");
	
	}

	/*
	@RequestMapping(value = "{uid}", produces = "text/html")
	public String edit(@PathVariable("uid") long uid, Model model) {

		Stockout stockout = stockoutService.get(uid);
		StockoutWrapper stockoutWrapper = new StockoutWrapper(stockout);

		model.addAttribute("stockout", stockoutWrapper);

		List<Product> products = productService.getAll();
		List<Facility> facilities = facilityService.getAll();
		
		List<ProductWrapper> productWrappers = new ArrayList<ProductWrapper>();
		for(Product p: products){
			ProductWrapper productWrapper = new ProductWrapper(p);
			productWrappers.add(productWrapper);
		}

		model.addAttribute("facilities", facilities);
		model.addAttribute("products", productWrappers);

		return "admin/stockouts/Edit";
	}

	@RequestMapping(value = "{uid}/delete", produces = "text/html")
	public String delete(@PathVariable("uid") String uid, Model model) {

		return "";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "text/html")
	public String update(StockoutWrapper stockout, Model uiModel) {
		System.out.println("Product:" + stockout.getProductUID());
		System.out.println("Facility:"
				+ stockout.getFacilityUID());

		return "redirect:/sows/admin/stockouts";
	}
	*/

}
