package za.org.opengov.stockout.web.admin.controller;

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

import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.medical.MedicineClassService;
import za.org.opengov.stockout.service.medical.MedicineService;
import za.org.opengov.stockout.service.medical.ProductService;
import za.org.opengov.stockout.web.admin.domain.MedicineWrapper;
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;

@Controller
@RequestMapping(value = "sows/admin/products")
public class ProductController extends AbstractPaginationController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private MedicineService medicineService;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") long page,
			Model model) {

		long totalItems = productService.getCount();

		int noOfPages = (int) Math.ceil((double) totalItems
				/ (double) RESULTS_PER_PAGE);

		List<Product> results = productService.getPage((int) page - 1,
				RESULTS_PER_PAGE);

		model.addAttribute("currentPage", page);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("results", results);

		return "admin/products/List";
	}
	
	@RequestMapping(value="new",method = RequestMethod.GET, produces = "text/html")
	public String NewPage(Model model){
		
		List<Medicine> medicines = medicineService.getAll();
		
		model.addAttribute("medicines", medicines);

		return("admin/products/New");
	}
	

	
	@ModelAttribute("productwrapper")
    private ProductWrapper getProductWrapper() {
        return new ProductWrapper();
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute ProductWrapper productWrapper,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			
			List<Medicine> medicines = medicineService.getAll();
			
			model.addAttribute("medicines", medicines);
			
			return"admin/products/New";
		}
		
		Product product = new Product();
		product.setName(productWrapper.getName());
		product.setDescription(productWrapper.getDescription());
		product.setMedicine(medicineService.get(productWrapper.getMedicineUid()));
		product.setPriceInclVAT(productWrapper.getPrice());
		product.setVolume(productWrapper.getVolume());
		product.setUid(productWrapper.getName().substring(0, 3)+(int)(Math.random()*100));
		
		productService.put(product);
		
		return ("redirect:/sows/admin/products");
	
	}
	
	@RequestMapping(value = "{uid}", produces = "text/html")
	public String edit(@PathVariable("uid") String uid, Model model) {

		List<Medicine> medicines = medicineService.getAll();
		Product product = productService.get(uid);
		ProductWrapper productWrapper = new ProductWrapper(product);
		
		model.addAttribute("productWrapper", productWrapper);
		model.addAttribute("medicines", medicines);
		model.addAttribute("product", product);
		
		

		return "admin/products/Edit";
	}

	@RequestMapping(value = "{uid}/delete", produces = "text/html")
	public String delete(@PathVariable("uid") String uid, Model model) {

		productService.remove(productService.get(uid));	
		
		return "redirect:/sows/admin/products";
	}

	@RequestMapping(value = "{uid}/update", method = RequestMethod.POST, produces = "text/html")
	public String update(@Valid @ModelAttribute ProductWrapper productWrapper,BindingResult result, 
			@PathVariable("uid") String uid,Model model) {
		
		if (result.hasErrors()) {
			List<Medicine> medicines = medicineService.getAll();
			Product product = productService.get(uid);
			
			model.addAttribute("medicines", medicines);
			
			return"admin/products/Edit";
		}
		
		Product product = productService.get(uid);
		
		product.setName(productWrapper.getName());
		product.setDescription(productWrapper.getDescription());
		product.setMedicine(medicineService.get(productWrapper.getMedicineUid()));
		product.setPriceInclVAT(productWrapper.getPrice());
		product.setVolume(productWrapper.getVolume());
		productService.put(product);
		return "redirect:/sows/admin/products";
	}
	

}
