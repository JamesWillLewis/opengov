package za.org.opengov.stockout.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceScheduler {
	
	/**
	 * Send email notifications to email list, every
	 * Monday, at 12:00 PM. 
	 * 
	 */
	@Scheduled(cron="0 0 12 ? * MON")
	public void sendNotifications(){
		System.out.println("Method called");
	}

}