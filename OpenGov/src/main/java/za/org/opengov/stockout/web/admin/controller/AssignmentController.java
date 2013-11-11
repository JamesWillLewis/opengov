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


/**Handles client-side viewing,editing and adding of assignments to the assignments table*/
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
	
	 /**Handles the listing of all data in the assignments table, uses pagination to display a limited number of
	  * results per page. Page numbers determine which results are returned to the client*/
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
	
	/**Handles creation of the New assignments page, list of issues and staffmembers are listed for user to assign
	 * issues to staff members*/
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
	
	/**Handles binding of user enter data in the select page to an appropriate wrapper class.
	 * Checks to see that the user enter data is valid.
	 * Uses the wrapper class and assignments service to create a new row of data in the assignmnents table*/
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

	/**Handles loading edit page for editing of information within a selected row in the assignments table.
	 * Data already present in the table is preloaded into a wrapper, to be preloaded into user selection/entry fields.
	 * uid is the primary key of the table that uniquely identifies an assignment */
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

	
	/**Handles deletion of a row in the assingments table
	 * assignment service is used to remove the row with the corresponding uid*/
	@RequestMapping(value = "{uid}/delete", produces = "text/html")
	public String delete(@PathVariable("uid") long uid, Model model) {
		
		assignmentService.remove(assignmentService.get(uid));
		
		return ("redirect:/sows/admin/assignments");
	}

	
	/** Handles user editing row data using the unchanged uid of the row to identify the corresponding
	 * row in the database to update. user updates are checked for validity and assignment service is
	 * called to update the correct row.*/
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

