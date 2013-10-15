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
