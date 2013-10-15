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

	public List<Stockout> findByFacility(String facilityCode);

	public List<Stockout> findByProduct(String productCode);

	public Stockout findByProductAndFacility(String productCode,
			String facilityCode);

	public List<Stockout> getMostCommonlyReportedStockoutsForFacility(
			String facilityCode, int limit);

	public List<Stockout> findAllOrderedUnresolvedStockouts();

	public List<Stockout> getStockoutsForFacilityOrderedByTimestamp(
			String facilityCode, int limit);

	
}
