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
import za.org.opengov.common.entity.StaffMember;
import za.org.opengov.common.service.AssignmentService;
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
import za.org.opengov.stockout.web.admin.domain.ProductWrapper;
import za.org.opengov.stockout.web.admin.domain.StaffMemberWrapper;
import za.org.opengov.stockout.web.admin.domain.StockoutWrapper;

@Controller
@RequestMapping(value = "sows/admin/assignments")
public class AssignmentController extends AbstractPaginationController {

	@Autowired
	private StockoutService stockoutService;
	
	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private StaffMemberService staffMemberService;
	
	 
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") long page,
			Model model) {

		long totalItems = assignmentService.getCount();

		int noOfPages = (int) Math.ceil((double) totalItems
				/ (double) RESULTS_PER_PAGE);

		List<Assignment> assignments = assignmentService.getPage((int) page - 1,
				RESULTS_PER_PAGE);

		model.addAttribute("currentPage", page);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("results", assignments);

		return "admin/assignments/List";
	}
	
	@RequestMapping(value="new",method = RequestMethod.GET, produces = "text/html")
	public String NewPage(Model model){
		
		List<Issue> issues = issueService.getAll();
		List<StaffMember> members = staffMemberService.getAll();
		
		
		model.addAttribute("issues", issues);
		model.addAttribute("staffmembers", members);
		
		
		return("admin/assignments/New");
	}
	
	
	@ModelAttribute("assignment")
    private AssignmentWrapper getAssignmentWrapper() {
        return new AssignmentWrapper();
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute AssignmentWrapper assignment,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			
			return"admin/assignments/New";
		}
		
		Assignment assign = new Assignment();
		assign.setIssue(issueService.get(assignment.getIssueUID()));
		assign.setStaffMember(staffMemberService.get(assignment.getStaffMemberUID()));
		
		System.out.println(assign.getIssue().getUid());
		System.out.println(assign.getStaffMember().getStaffCode());
		System.out.println(assign.getStaffMember().getName());
		
		assignmentService.put(assign);
		
		return ("redirect:/sows/admin/assignments");
	
	}

	
	@RequestMapping(value = "{uid}", produces = "text/html")
	public String edit(@PathVariable("uid") long uid, Model model) {

		List<Issue> issues = issueService.getAll();
		List<StaffMember> members = staffMemberService.getAll();
		AssignmentWrapper assignment = new AssignmentWrapper(assignmentService.get(uid));
		
		model.addAttribute("uid",uid);
		model.addAttribute("issues", issues);
		model.addAttribute("staffmembers", members);
		model.addAttribute("assign", assignment);
		
		return "admin/assignments/Edit";
	}

	@RequestMapping(value = "{uid}/delete", produces = "text/html")
	public String delete(@PathVariable("uid") long uid, Model model) {
		
		assignmentService.remove(assignmentService.get(uid));
		
		return ("redirect:/sows/admin/assignments");
	}

	@RequestMapping(value = "{uid}/update", method = RequestMethod.POST, produces = "text/html")
	public String update(@Valid @ModelAttribute AssignmentWrapper assignment,BindingResult result, 
			@PathVariable("uid") long uid,Model model) {
		
		Assignment assign = assignmentService.get(uid);
		assign.setIssue(issueService.get(assignment.getIssueUID()));
		assign.setStaffMember(staffMemberService.get(assignment.getStaffMemberUID()));
		assignmentService.put(assign);

		return "redirect:/sows/admin/assignments";
	}

}

