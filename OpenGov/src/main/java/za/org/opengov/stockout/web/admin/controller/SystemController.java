package za.org.opengov.stockout.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import za.org.opengov.common.entity.config.MailingEntry;
import za.org.opengov.common.entity.security.User;
import za.org.opengov.common.service.config.MailingEntryService;
import za.org.opengov.common.service.config.SystemParameterService;
import za.org.opengov.common.service.security.UserService;
import za.org.opengov.stockout.schedule.StockoutNotificationService;
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
			BindingResult result, Model model) {

		systemParameterService.setParam("stockout.ussd.enabled",
				systemParamsWrapper.getEnableUSSDService() ? "1" : "0");
		systemParameterService.setParam("stockout.notifications.period",
				systemParamsWrapper.getPeriod());

		return "redirect:/sows/admin/system";
	}

}
