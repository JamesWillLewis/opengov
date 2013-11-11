package za.org.opengov.stockout.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import za.org.opengov.common.entity.Assignment;
import za.org.opengov.common.entity.Issue;
import za.org.opengov.common.entity.StaffMember;
import za.org.opengov.common.entity.config.MailingEntry;
import za.org.opengov.common.entity.config.MailingRole;
import za.org.opengov.common.entity.security.User;
import za.org.opengov.common.service.config.MailingEntryService;
import za.org.opengov.common.service.config.MailingRoleService;
import za.org.opengov.common.service.config.SystemParameterService;
import za.org.opengov.common.service.security.UserService;
import za.org.opengov.stockout.schedule.StockoutNotificationService;
import za.org.opengov.stockout.web.admin.domain.AdminWrapper;
import za.org.opengov.stockout.web.admin.domain.AssignmentWrapper;
import za.org.opengov.stockout.web.admin.domain.ListMemberWrapper;
import za.org.opengov.stockout.web.admin.domain.SystemParamsWrapper;

@Controller
@RequestMapping("sows/admin/system")
public class SystemController extends AbstractPaginationController {

	@Autowired
	private SystemParameterService systemParameterService;

	@Autowired
	private MailingEntryService mailingEntryService;

	@Autowired
	private StockoutNotificationService stockoutNotificationService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailingRoleService mailingRoleService;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String view(Model model) {

		List<MailingEntry> mailingEntries = mailingEntryService
				.getAllMailingEntriesForRole(stockoutNotificationService
						.getStockoutReportRoleTag());

		List<User> users = userService.getUsersWithRole("SOWS-ADMIN");

		SystemParamsWrapper paramsWrapper = new SystemParamsWrapper();
		paramsWrapper.setEnableUSSDService(systemParameterService.getParam(
				"stockout.ussd.enabled").equals("1"));
		paramsWrapper.setPeriod(systemParameterService
				.getParam("stockout.notifications.period"));

		model.addAttribute("mailingEntries", mailingEntries);
		model.addAttribute("users", users);

		model.addAttribute("params", paramsWrapper);

		// set various time periods
		Map<String, String> allPeriods = new HashMap<String, String>();
		for (StockoutNotificationService.Period period : StockoutNotificationService.Period
				.values()) {
			allPeriods.put("" + period.getId(), period.getUserReadable());
		}
		model.addAttribute("allPeriods", allPeriods);

		return "admin/system/View";
	}

	@RequestMapping(value = "updateSettings", method = RequestMethod.POST, produces = "text/html")
	public String updateSettings(SystemParamsWrapper systemParamsWrapper,
			BindingResult result,RedirectAttributes redirectAttrs, Model model) {

		systemParameterService.setParam("stockout.ussd.enabled",
				systemParamsWrapper.getEnableUSSDService() ? "1" : "0");
		systemParameterService.setParam("stockout.notifications.period",
				systemParamsWrapper.getPeriod());
		
		String UssdEnabled;
		if (systemParamsWrapper.getEnableUSSDService()){
			UssdEnabled = "enabled";
		}
		else{UssdEnabled ="disabled";}
		
		redirectAttrs.addFlashAttribute("ussd_message", "USSD service is now " + UssdEnabled);
		redirectAttrs.addFlashAttribute("notification_message", "Notifications will be sent " + systemParamsWrapper.getPeriod());
		
		return "redirect:/sows/admin/system";
	}
	
	
	@RequestMapping(value="newAdmin",method = RequestMethod.GET, produces = "text/html")
	public String NewPage(Model model){
		
		
		return("admin/system/Edit");
	}
	
	@ModelAttribute("admin")
    private AdminWrapper getAdminWrapper() {
        return new AdminWrapper();
    }
	
	
	@RequestMapping(value = "/addAdmin", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute AdminWrapper admin,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			
			return"admin/system/Edit";
		}
		
		User user = new User();
		user.setUsername(admin.getName());
		user.setPassword(admin.getPassword());
		user.setRoles("SOWS-ADMIN");
		userService.saveEncrypted(user);
		
		return ("redirect:/sows/admin/system");
	
	}
	
	
	@RequestMapping(value = "{userName}/deleteAdmin", produces = "text/html")
	public String deleteAdmin(@PathVariable("userName") String userName, Model model) {
		
		userService.remove(userService.get(userName));
		
		return ("redirect:/sows/admin/system");
	}
	
	
	
	
	@ModelAttribute("listMember")
    private ListMemberWrapper getListMemberWrapper() {
        return new ListMemberWrapper();
    }
	
	
	@RequestMapping(value = "/addListMember", method = RequestMethod.POST, produces = "text/html")
	public String add(@Valid @ModelAttribute ListMemberWrapper listMember,BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			
			return"admin/system/ListEdit";
		}
		
		MailingEntry mailingMember = new MailingEntry();
		mailingMember.setName(listMember.getName());
		mailingMember.setAddress(listMember.getEmail());
		
		
		MailingRole mailingRole = new MailingRole();
		mailingRole.setMailingEntry(mailingMember);
		mailingRole.setRoleID("STOCKOUT_NOTIFICATIONS");
		
		mailingEntryService.put(mailingMember);
		mailingRoleService.put(mailingRole);
		
		
		return ("redirect:/sows/admin/system");
	
	}
	
	@RequestMapping(value="newListMember",method = RequestMethod.GET, produces = "text/html")
	public String NewMailingPage(Model model){
		
		
		return("admin/system/ListEdit");
	}
	
	
	@RequestMapping(value = "{listName}/deleteMember", produces = "text/html")
	public String deleteMember(@PathVariable("listName") String listName, Model model) {
		
		mailingEntryService.remove(mailingEntryService.get(listName));
		
		return ("redirect:/sows/admin/system");
	}
	

}
