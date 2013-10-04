package za.org.opengov.stockout.service;

import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.Subject;

public interface StockoutReportService extends AbstractService<StockoutReport, Long>{

	/**
	 * Submit a stock-out report.
	 * 
	 * @param stockoutReport
	 * @return Handle for the report (primary key)
	 */
	public long submitStockoutReport(StockoutReport stockoutReport);

	/**
	 * 
	 * @param productCode
	 * @param facilityCode
	 * @param reporter
	 * @param reportee
	 * @param cause
	 * @param reportedToDOH
	 * @param resolved
	 * @return 
	 * @throws IllegalArgumentException if productCode or facilityCode are null or empty strings.
	 */
	public long submitStockoutReport(String productCode, String facilityCode,
			Subject reporter, String cause,
			boolean reportedToDOH)
			throws IllegalArgumentException;
	
	
	
	public List<StockoutReport> getRecentlyReportedStockoutsForDisease();
	
	public List<StockoutReport> getRecentlyReportedStockouts(int limit);
	

}
