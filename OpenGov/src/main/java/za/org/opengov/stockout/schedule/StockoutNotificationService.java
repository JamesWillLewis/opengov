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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import za.org.opengov.common.entity.config.MailingEntry;
import za.org.opengov.common.service.config.MailingEntryService;
import za.org.opengov.common.service.config.SystemParameterService;
import za.org.opengov.common.util.MailUtil;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.service.StockoutService;

/**
 * A service which sends scheduled notifications of all outstanding stock-outs
 * to a mailing list. The notifications can be scheduled to be monthly, weekly,
 * daily, hourly, or disabled.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 * 
 */
@Service
public class StockoutNotificationService {

	/**
	 * Different time periods in which the notifications can be sent.
	 * 
	 */
	public enum Period {

		MONTHLY("Monthly", 0), WEEKLY("Weekly", 1), DAILY("Daily", 2), HOURLY(
				"Hourly", 3), NONE("Disabled", 4);

		private String userReadable;
		private int id;

		private Period(String userReadable, int id) {
			this.userReadable = userReadable;
			this.id = id;
		}

		public String getUserReadable() {
			return userReadable;
		}

		public int getId() {
			return id;
		}

	}

	@Autowired
	private StockoutService stockoutService;

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private MailingEntryService mailingEntryService;

	@Autowired
	private SystemParameterService systemParameterService;

	/**
	 * The given sender address.
	 */
	private String sender;

	/**
	 * Tag of the mailing list for this notification service.
	 */
	private String stockoutReportRoleTag;

	@Scheduled(cron = "${cron.stockout.notification.monthly}")
	public void sendNotificationsMonthly() {
		int period = Integer.valueOf(systemParameterService
				.getParam("stockout.notifications.period"));
		if (period == Period.MONTHLY.id) {
			sendStockoutNotifications();
		}
	}

	@Scheduled(cron = "${cron.stockout.notification.weekly}")
	public void sendNotificationsWeekly() {
		int period = Integer.valueOf(systemParameterService
				.getParam("stockout.notifications.period"));
		if (period == Period.WEEKLY.id) {
			sendStockoutNotifications();
		}
	}

	@Scheduled(cron = "${cron.stockout.notification.daily}")
	public void sendNotificationsDaily() {
		int period = Integer.valueOf(systemParameterService
				.getParam("stockout.notifications.period"));
		if (period == Period.DAILY.id) {
			sendStockoutNotifications();
		}
	}

	@Scheduled(cron = "${cron.stockout.notification.hourly}")
	public void sendNotificationsHourly() {
		int period = Integer.valueOf(systemParameterService
				.getParam("stockout.notifications.period"));
		if (period == Period.HOURLY.id) {
			sendStockoutNotifications();
		}
	}

	/**
	 * 
	 * 
	 */
	public void sendStockoutNotifications() {

		// update priorities of all stock-outs
		// TODO: don't update stockouts which have been resolved?
		stockoutService.updateAllStockoutPriorities();

		// all stock-outs where the issue state != resolved or closed
		List<Stockout> stockouts = stockoutService.getAllUnresolvedStockouts();

		// list of all addresses for the StockOut service
		List<MailingEntry> mailingList = mailingEntryService
				.getAllMailingEntriesForRole(stockoutReportRoleTag);

		String body = "There are currently " + stockouts.size()
				+ " unresolved stock-outs.";
		body += "\n\n The following list is ordered from highest to lowest priority:";

		for (int i = 0; i < stockouts.size(); i++) {
			Stockout s = stockouts.get(i);
			body += "\n" + (i + 1) + ". "
					+ s.getFacility().getOfficialDOHName() + " " + s.getFacility().getFacilityType().getReadable() + " - "
					+ s.getProduct().getName() + " ("
					+ s.getProduct().getDescription() + ")";
		}

		for (MailingEntry mailingEntry : mailingList) {
			String mail = "Hello " + mailingEntry.getName() + ",\n\n";
			mail += body;

			mailUtil.sendMail(sender, mailingEntry.getAddress(),
					"OpenGov: StockOut Notification", mail);
		}
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
