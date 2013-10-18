package za.org.opengov.stockout.web.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import za.org.opengov.common.entity.IssueState;
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
import za.org.opengov.stockout.web.admin.domain.AssignmentWrapper;
import za.org.opengov.stockout.web.admin.domain.IssueWrapper;
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;
import za.org.opengov.stockout.web.admin.domain.StockoutWrapper;

@Controller
@RequestMapping(value = "sows/admin/issues")
public class IssueController extends AbstractPaginationController {

	@Autowired
	private StockoutService stockoutService;
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ProductService productService;
	
 
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") long page,
			Model model) {

		long totalItems = issueService.getCount();

		int noOfPages = (int) Math.ceil((double) totalItems
				/ (double) RESULTS_PER_PAGE);
		
		List<Issue> issues = issueService.getPage((int) page - 1,
				RESULTS_PER_PAGE);
		

		model.addAttribute("currentPage", page);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("issues", issues);

		return "admin/issues/List";
	}

	
	@RequestMapping(value = "{uid}", produces = "text/html")
	public String edit(@PathVariable("uid") long uid, Model model) {

		IssueState[] is = IssueState.values(); 
		model.addAttribute("states",is);
		model.addAttribute("uid",uid);
		
		return "admin/issues/Edit";
	}

	@ModelAttribute("issue")
    private IssueWrapper getState() {
        return new IssueWrapper();
    }

	@RequestMapping(value = "{uid}/update", method = RequestMethod.POST, produces = "text/html")
	public String update(@Valid @ModelAttribute IssueWrapper issue,BindingResult result, 
			@PathVariable("uid") long uid,Model model) {
		
		Issue newIssue = issueService.get(uid);
		IssueState iss = (issue.getIssueState());
		//issue.setState(new State(state));
		newIssue.setState(iss);

		issueService.put(newIssue);

		return "redirect:/sows/admin/issues";
	}

}

