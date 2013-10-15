/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import za.org.opengov.common.service.config.SystemParameterService;
import za.org.opengov.common.util.MailUtil;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.service.StockoutService;

@Component
public class StockoutNotificationService {

	@Autowired
	private StockoutService stockoutService;

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private MailingEntryService mailingEntryService;

	@Autowired
	private SystemParameterService systemParameterService;

	private String sender;

	private String stockoutReportRoleTag;

	/**
	 * Send email notifications to email list, every Monday, at 11:00 AM.
	 * 
	 */
	@Scheduled(cron = "${cron.stockout.notification}")
	public void sendNotifications() {
		if (systemParameterService.getParam("stockout.notifications.enabled")
				.equals("1")) {
			sendStockoutNotifications();
		}
	}

	public void sendStockoutNotifications() {
		stockoutService.updateAllStockoutPriorities();
		List<Stockout> stockouts = stockoutService.getAllUnresolvedStockouts();

		List<MailingEntry> mailingList = mailingEntryService
				.getAllMailingEntriesForRole(stockoutReportRoleTag);

		String mail = "there are " + stockouts.size()
				+ " stockouts with priority "
				+ stockouts.get(0).getIssue().getPriority();
		// TODO
		 mailUtil.sendMail(sender, "james.will.lewis@gmail.com", "StockOut report", mail);

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
