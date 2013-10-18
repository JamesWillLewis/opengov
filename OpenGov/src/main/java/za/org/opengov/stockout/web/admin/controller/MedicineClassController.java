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

import za.org.opengov.common.entity.Assignment;
import za.org.opengov.common.entity.Issue;
import za.org.opengov.common.entity.StaffMember;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutService;
import za.org.opengov.stockout.service.medical.MedicineClassService;
import za.org.opengov.stockout.service.medical.ProductService;
import za.org.opengov.stockout.web.admin.domain.AssignmentWrapper;
import za.org.opengov.stockout.web.admin.domain.MedicineClassWrapper;
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;
import za.org.opengov.stockout.web.admin.domain.StockoutWrapper;

@Controller
@RequestMapping(value = "sows/admin/medicineclasses")
public class MedicineClassController extends AbstractPaginationController {

	@Autowired
	private MedicineClassService medicineClassService;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") long page,
			Model model) {

		long totalItems = medicineClassService.getCount();

		int noOfPages = (int) Math.ceil((double) totalItems
				/ (double) RESULTS_PER_PAGE);

		List<MedicineClass> results = medicineClassService.getPage((int) page - 1,
				RESULTS_PER_PAGE);

		model.addAttribute("currentPage", page);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("results", results);

		return "admin/medicineclasses/List";
	}
	
	@RequestMapping(value="new",method = RequestMethod.GET, produces = "text/html")
	public String NewPage(Model model){
		

		return("admin/medicineclasses/New");
	}
	
	
	@ModelAttribute("medicineclasswrapper")
    private MedicineClassWrapper getMedicineClassWrapper() {
        return new MedicineClassWrapper();
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute MedicineClassWrapper medicineClassWrapper,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			
			return"admin/medicineclass/New";
		}
		
		MedicineClass medicineClass = new MedicineClass();
		medicineClass.setUid(medicineClassWrapper.getUid());
		medicineClassService.put(medicineClass);
		
		return ("redirect:/sows/admin/medicineclasses");
	
	}



	@RequestMapping(value = "{uid}/delete", produces = "text/html")
	public String delete(@PathVariable("uid") String uid, Model model) {
		
		medicineClassService.remove(medicineClassService.get(uid));
		
		return "redirect:/sows/admin/medicineclasses";
	}

	

}
