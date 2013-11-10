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
package za.org.opengov.stockout.dao;

import java.util.List;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.common.entity.config.MailingEntry;
import za.org.opengov.stockout.entity.StockoutReport;

/**
 * Data Access Object for {@link StockoutReport}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface StockoutReportDao extends AbstractDao<StockoutReport, Long> {

	/**
	 * Get most recent stock-out reports.
	 * 
	 * @param limit
	 *            Maximum number of reports to return.
	 * @return List of most recently submitted stock-out reports.
	 */
	public List<StockoutReport> findMostRecentStockouts(int limit);

	/**
	 * Find all reports of a stock-out of a certain product at a certain
	 * facility.
	 * 
	 * @param productCode
	 *            Product UID.
	 * @param facilityCode
	 *            Facility UID.
	 * @return List of stock-out reports which are of the product at the
	 *         facility.
	 */
	public List<StockoutReport> findForFacilityAndProduct(String productCode,
			String facilityCode);

}
