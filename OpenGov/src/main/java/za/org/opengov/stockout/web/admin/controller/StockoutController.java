package za.org.opengov.stockout.web.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import za.org.opengov.common.entity.Issue;
import za.org.opengov.common.entity.StaffMember;
import za.org.opengov.common.service.IssueService;
import za.org.opengov.common.service.StaffMemberService;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutService;
import za.org.opengov.stockout.service.medical.ProductService;
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;
import za.org.opengov.stockout.web.admin.domain.StockoutWrapper;

@Controller
@RequestMapping(value = "sows/admin/stockouts")
public class StockoutController extends AbstractPaginationController {

	@Autowired
	private StockoutService stockoutService;
	
	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ProductService productService;
	
 
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") long page,
			Model model) {

		long totalItems = stockoutService.getCount();

		int noOfPages = (int) Math.ceil((double) totalItems
				/ (double) RESULTS_PER_PAGE);

		List<Stockout> stockouts = stockoutService.getPage((int) page - 1,
				RESULTS_PER_PAGE);

		model.addAttribute("currentPage", page);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("stockouts", stockouts);

		return "admin/stockouts/List";
	}

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
		Stockout updateStockout = stockout.getStockout();
		
		updateStockout.setProduct(productService.get(stockout.getProductUID()));
		updateStockout.setFacility(facilityService.get(stockout.getFacilityUID()));
		stockoutService.put(updateStockout);

		return "redirect:/sows/admin/stockouts";
	}

}
