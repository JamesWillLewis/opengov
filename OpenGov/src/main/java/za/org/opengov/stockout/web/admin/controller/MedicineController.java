package za.org.opengov.stockout.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutService;
import za.org.opengov.stockout.service.medical.MedicineService;
import za.org.opengov.stockout.service.medical.ProductService;
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;
import za.org.opengov.stockout.web.admin.domain.StockoutWrapper;

@Controller
@RequestMapping(value = "sows/admin/medicines")
public class MedicineController extends AbstractPaginationController {
;

	@Autowired
	private MedicineService medicineService;

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
