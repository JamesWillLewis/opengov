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
import za.org.opengov.stockout.web.admin.domain.StaffMemberWrapper;
import za.org.opengov.stockout.web.admin.domain.StockoutWrapper;
import za.org.opengov.stockout.web.domain.PublicStockoutReport;

@Controller
@RequestMapping(value = "sows/admin/staffmembers")
public class StaffMemberController extends AbstractPaginationController {

	@Autowired
	private StockoutService stockoutService;
	
	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private StaffMemberService staffMemberService;
	
	 
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false, defaultValue = "1") long page,
			Model model) {

		long totalItems = staffMemberService.getCount();

		int noOfPages = (int) Math.ceil((double) totalItems
				/ (double) RESULTS_PER_PAGE);

		List<StaffMember> staffMembers = staffMemberService.getPage((int) page - 1,
				RESULTS_PER_PAGE);

		model.addAttribute("currentPage", page);
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("staffmembers", staffMembers);

		return "admin/staffmembers/List";
	}
	
	
	@RequestMapping(value="new",method = RequestMethod.GET, produces = "text/html")
	public String NewPage(Model model){
		
		
		return("admin/staffmembers/New");
	}
	
	
	@ModelAttribute("staffMember")
    private StaffMemberWrapper getStaffMember() {
        return new StaffMemberWrapper();
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute StaffMemberWrapper staffMember,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			
			return"admin/staffmembers/New";
		}
		
		System.out.println(staffMember.getName());
		System.out.println(staffMember.getSurname());
		System.out.println(staffMember.getStaffCode());
		
		StaffMember staff = new StaffMember();
		staff.setName(staffMember.getName());
		staff.setSurname(staffMember.getSurname());
		staff.setStaffCode(staffMember.getStaffCode());
		staffMemberService.put(staff);
		
		return ("redirect:/sows/admin/staffmembers");
	
	}
	
	
	@RequestMapping(value = "{uid}", produces = "text/html")
	public String edit(@PathVariable("uid") long uid, Model model) {

		StaffMemberWrapper staff = new StaffMemberWrapper(staffMemberService.get(uid));
		
		model.addAttribute("uid", uid);
		model.addAttribute("staffmember", staff);

		return "admin/staffmembers/Edit";
	}

	@RequestMapping(value = "{uid}/delete", produces = "text/html")
	public String delete(@PathVariable("uid") long uid, Model model) {
		
		StaffMember staffMember = staffMemberService.get(uid);
		staffMemberService.remove(staffMember);
		
		return "redirect:/sows/admin/staffmembers";
	}
	
	
	@RequestMapping(value = "{uid}/update", method = RequestMethod.POST, produces = "text/html")
	public String update(@Valid @ModelAttribute StaffMemberWrapper staff,BindingResult result, 
			@PathVariable("uid") long uid,Model model) {
		
		StaffMember staffmem = new StaffMember();
		staffmem.setName(staff.getName());
		staffmem.setSurname(staff.getSurname());
		staffmem.setStaffCode(staff.getStaffCode());
		staffmem.setUid(uid);
		staffMemberService.put(staffmem);

		return "redirect:/sows/admin/staffmembers";
	}

}

