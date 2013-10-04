package za.org.opengov.stockout.schedule;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.MonthDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import za.org.opengov.common.entity.config.MailingEntry;
import za.org.opengov.common.service.config.MailingEntryService;
import za.org.opengov.common.util.MailUtil;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.service.StockoutService;

@Component("stockoutNotificationService")
public class StockoutNotificationService {

	@Autowired
	private StockoutService stockoutService;

	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private MailingEntryService mailingEntryService;
	
	private String sender;
	
	private String stockoutReportRoleTag;

	

	/**
	 * Send email notifications to email list, every Monday, at 11:00 AM.
	 * 
	 */
	@Scheduled(cron = "${cron.stockout.notification}")
	public void sendNotifications() {
		sendStockoutNotifications();
	}

	public void sendStockoutNotifications() {
		stockoutService.updateAllStockoutPriorities();
		List<Stockout> stockouts = stockoutService.getAllUnresolvedStockouts();

		List<MailingEntry> mailingList = mailingEntryService.getAllMailingEntriesForRole(stockoutReportRoleTag); 

		String mail = "there are " + stockouts.size()
				+ " stockouts with priority "
				+ stockouts.get(0).getIssue().getPriority();
		//TODO
		//mailUtil.sendMail(sender, to, "StockOut report", mail);

	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}
	
	public void setStockoutReportRoleTag(String stockoutReportRoleTag) {
		this.stockoutReportRoleTag = stockoutReportRoleTag;
	}
	
	public String getStockoutReportRoleTag() {
		return stockoutReportRoleTag;
	}
	

}
