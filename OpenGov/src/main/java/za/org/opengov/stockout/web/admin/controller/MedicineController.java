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

import za.org.opengov.common.entity.StaffMember;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutService;
import za.org.opengov.stockout.service.medical.MedicineClassService;
import za.org.opengov.stockout.service.medical.MedicineService;
import za.org.opengov.stockout.service.medical.ProductService;
import za.org.opengov.stockout.web.admin.domain.MedicineClassWrapper;
import za.org.opengov.stockout.web.admin.domain.MedicineWrapper;
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;
import za.org.opengov.stockout.web.admin.domain.StaffMemberWrapper;
import za.org.opengov.stockout.web.admin.domain.StockoutWrapper;

@Controller
@RequestMapping(value = "sows/admin/medicines")
public class MedicineController extends AbstractPaginationController {
;

	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private MedicineClassService medicineClassService;
	

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") long page,
			Model model) {

		long totalItems = medicineService.getCount();

		int noOfPages = (int) Math.ceil((double) totalItems
				/ (double) RESULTS_PER_PAGE);

		List<Medicine> results = medicineService.getPage((int) page - 1,
				RESULTS_PER_PAGE);

		model.addAttribute("currentPage", page);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("results", results);

		return "admin/medicines/List";
	}
	
	@RequestMapping(value="new",method = RequestMethod.GET, produces = "text/html")
	public String NewPage(Model model){
		
		List<MedicineClass> medicineClasses = medicineClassService.getAll();
		
		model.addAttribute("medicineClasses", medicineClasses);

		return("admin/medicines/New");
	}
	
	
	@ModelAttribute("medicinewrapper")
    private MedicineWrapper getMedicineWrapper() {
        return new MedicineWrapper();
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute MedicineWrapper medicineWrapper,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			
			return"admin/medicines/New";
		}
		
		Medicine medicine = new Medicine();
		medicine.setName(medicineWrapper.getName());
		
		medicine.setMedicineClass(medicineClassService.get(medicineWrapper.getMedicineClassUID()));
		medicineService.put(medicine);
		
		return ("redirect:/sows/admin/medicines");
	
	}

	
	@RequestMapping(value = "{uid}", produces = "text/html")
	public String edit(@PathVariable("uid") long uid, Model model) {

		List<MedicineClass> medicineClasses = medicineClassService.getAll();
		
		model.addAttribute("medicineClasses", medicineClasses);

		return "admin/medicines/Edit";
	}

	@RequestMapping(value = "{uid}/delete", produces = "text/html")
	public String delete(@PathVariable("uid") long uid, Model model) {

		medicineService.remove(medicineService.get(uid));	
		
		return "redirect:/sows/admin/medicines";
	}

	@RequestMapping(value = "{uid}/update", method = RequestMethod.POST, produces = "text/html")
	public String update(@Valid @ModelAttribute MedicineWrapper medicineWrapper,BindingResult result, 
			@PathVariable("uid") long uid,Model model) {
		
		if (result.hasErrors()) {
			List<MedicineClass> medicineClasses = medicineClassService.getAll();
			
			model.addAttribute("medicineClasses", medicineClasses);
			return"admin/medicines/Edit";
		}
		
		Medicine medicine = medicineService.get(uid);
		medicine.setMedicineClass(medicineClassService.get(medicineWrapper.getMedicineClassUID()));
		medicine.setName(medicineWrapper.getName());
		medicineService.put(medicine);

		return "redirect:/sows/admin/medicines";
	}

}
