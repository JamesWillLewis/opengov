package za.org.opengov.stockout.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import za.org.opengov.common.util.MailUtil;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.service.StockoutService;

@Component
public class NotificationService {

	@Autowired
	private StockoutService stockoutService;
	
	@Autowired
	private MailUtil mailUtil;

	/**
	 * Send email notifications to email list, every Monday, at 11:00 AM.
	 * 
	 */
	@Scheduled(cron = "${cron.stockout.notification}")
	public void sendNotifications() {
		sendStockoutNotifications();
	}

	public void sendStockoutNotifications() {
		List<Stockout> stockouts = stockoutService.getAllUnresolvedStockouts();
		String mail = "";
		
		//mailUtil.sendMail(from, to, subject, msg)
		
	}

}
