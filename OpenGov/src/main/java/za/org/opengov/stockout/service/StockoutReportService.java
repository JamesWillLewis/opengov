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
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.Subject;

/**
 * Service (business object) class for {@link StockoutReport}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface StockoutReportService extends
		AbstractService<StockoutReport, Long> {

	/**
	 * Submit a stock-out report. Should not be called directly - rather use
	 * other submitStockoutReport() method to ensure all prerequisite logic is
	 * performed. <br>
	 * <br>
	 * <br>
	 * <b> This method is marked to become private. </b>
	 * 
	 * @param stockoutReport
	 * @return Handle for the report (primary key)
	 */
	public long submitStockoutReport(StockoutReport stockoutReport);

	/**
	 * Submit a stock-out report. When submitting a stock-out report, this
	 * method should always be used, since it handles generation and assignment
	 * of various other associations/fields which a stock-out report requires,
	 * including generating an open issue, setting time-stamps, checking for an
	 * existing stock-out and creating a new instance if necessary, etc.
	 * 
	 * @param productCode
	 * @param facilityCode
	 * @param reporter
	 * @param reportee
	 * @param cause
	 * @param reportedToDOH
	 * @param resolved
	 * @return
	 * @throws IllegalArgumentException
	 *             if productCode or facilityCode are null or empty strings.
	 */
	public long submitStockoutReport(String productCode, String facilityCode,
			Subject reporter, String cause, boolean reportedToDOH)
			throws IllegalArgumentException;



	/**
	 * Get ordered list (most-recent first) of recent stock-outs.
	 * 
	 * @param limit Maximum number of elements to return.
	 * @return List of most recent stock-out reports. 
	 */
	public List<StockoutReport> getRecentlyReportedStockouts(int limit);

}
