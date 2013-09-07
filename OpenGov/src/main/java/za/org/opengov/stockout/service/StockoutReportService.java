package za.org.opengov.stockout.service;

import za.org.opengov.stockout.entity.StockoutReport;

public interface StockoutReportService {
	
	/**
	 * Submit a stock-out report.
	 * 
	 * @param stockoutReport
	 * @return Handle for the report (primary key)
	 */
	public long submitStockoutReport(StockoutReport stockoutReport);

}
