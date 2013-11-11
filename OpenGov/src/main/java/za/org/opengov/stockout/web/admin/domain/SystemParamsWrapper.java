package za.org.opengov.stockout.web.admin.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import za.org.opengov.stockout.schedule.StockoutNotificationService;

/**Wraps data used for client-side changing/editing of system settings*/
public class SystemParamsWrapper {

	private String period;

	private boolean enableUSSDService;

	public SystemParamsWrapper() {
	}

	public String getPeriod() {
		return period;
	}
	
	public void setPeriod(String period) {
		this.period = period;
	}

	public boolean getEnableUSSDService() {
		return enableUSSDService;
	}

	public void setEnableUSSDService(boolean enableUSSDService) {
		this.enableUSSDService = enableUSSDService;
	}

}
