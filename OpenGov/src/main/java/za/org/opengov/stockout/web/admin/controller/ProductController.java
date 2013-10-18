package za.org.opengov.stockout.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.medical.ProductService;
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;

@Controller
@RequestMapping(value = "sows/admin/products")
public class ProductController extends AbstractPaginationController {

	@Autowired
	private ProductService productService;

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

	
	/*@RequestMapping(value = "{uid}", produces = "text/html")
	public String edit(@PathVariable("uid") String uid, Model model) {

		Product product = productService.get(uid);
		ProductWrapper productWrapper = new ProductWrapper(product);

		model.addAttribute("product", productWrapper);

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
