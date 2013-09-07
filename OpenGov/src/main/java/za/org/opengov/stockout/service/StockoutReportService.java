package za.org.opengov.stockout.service;

import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.Subject;

public interface StockoutReportService {

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
			Subject reporter, Subject reportee, String cause,
			boolean reportedToDOH, boolean resolved)
			throws IllegalArgumentException;

}
