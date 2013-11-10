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
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Product;

/**
 * Data Access Object for {@link Stockout}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface StockoutDao extends AbstractDao<Stockout, Long> {

	/**
	 * Find all stock-outs at the given facility.
	 * 
	 * @param facilityCode
	 *            Facility UID.
	 * @return List of stock-outs.
	 */
	public List<Stockout> findByFacility(String facilityCode);

	/**
	 * Find all stock-outs of the given product.
	 * 
	 * @param productCode
	 *            Product UID.
	 * @return List of products.
	 */
	public List<Stockout> findByProduct(String productCode);

	/**
	 * Find stock-out of the given product for the given facility.
	 * 
	 * @param productCode
	 *            Product UID.
	 * @param facilityCode
	 *            Facility UID.
	 * @return Stockout instance, or <b>null</b> if no such stock-out is found.
	 */
	public Stockout findByProductAndFacility(String productCode,
			String facilityCode);

	/**
	 * Get most commonly reported stock-out at a particular facility.
	 * 
	 * @param facilityCode
	 *            Facility UID.
	 * @param limit
	 *            Maximum number of stock-outs to return.
	 * 
	 * @return The most common stock-outs, as determined by number of stock-out
	 *         reports for each stock-out. The list is limited to the specified
	 *         limit.
	 */
	public List<Stockout> getMostCommonlyReportedStockoutsForFacility(
			String facilityCode, int limit);

	/**
	 * Find all unresolved stock-outs.
	 * 
	 * @return List of unresolved stock-outs.
	 */
	public List<Stockout> findAllOrderedUnresolvedStockouts();

	/**
	 * Return a list of stock-outs, at a particular facility, ordered by the
	 * time-stamp (when the issue was opened) of the stock-out.
	 * 
	 * @param facilityCode
	 *            Facility UID.
	 * @param limit
	 *            Maximum number of stock-outs to return.
	 * @return List of stock-outs.
	 */
	public List<Stockout> getStockoutsForFacilityOrderedByTimestamp(
			String facilityCode, int limit);

}
