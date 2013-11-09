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
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;

/**
 * Service (business object) class for {@link Stockout}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface StockoutService extends AbstractService<Stockout, Long> {

	/**
	 * Persist the stockout.
	 * 
	 * @deprecated All services now have a 'put' method.
	 * @param stockout
	 */
	public void saveStockout(Stockout stockout);

	/**
	 * 
	 * @deprecated All services now have a 'getAll' method
	 * @return
	 */
	public List<Stockout> getAllStockouts();

	/**
	 * All stock-outs which occurred at the specified facility UID.
	 * 
	 * @param facilityCode
	 *            Facility UID/Code
	 * @return List of stockouts
	 */
	public List<Stockout> getAllStockoutsForFacility(String facilityCode);

	/**
	 * Find all stock-outs of the given product, for all facilities.
	 * 
	 * @param productCode
	 *            Product code.
	 * @return List of stock-outs.
	 */
	public List<Stockout> getAllStockoutsForProduct(String productCode);

	/**
	 * Find a stock-out of a particular product at a facility. If there are no
	 * stock-outs of the product at the facility, null is returned.
	 * 
	 * @param facilityCode
	 *            Facility code.
	 * @param productCode
	 *            Product code.
	 * @return The Stockout instance, or <b>null</b> if there is no stock-out.
	 */
	public Stockout getStockout(String facilityCode, String productCode);

	/**
	 * Get most commonly reported stock-out at a particular facility.
	 * 
	 * @param facilityCode
	 *            Facility UID.
	 * @return The most common stock-out, as determined by number of stock-out
	 *         reports.
	 */
	public Stockout getMostCommonlyReportedStockoutForFacility(
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
	 * Finds all unresolved stock-outs, meaning that the flag <i>resolved </i>
	 * is equal to <b>false</b>.
	 * 
	 * @return List of all unresolved stock-outs.
	 */
	public List<Stockout> getAllUnresolvedStockouts();

	/**
	 * 
	 * @param facilityCode
	 * @param limit
	 * @return
	 */
	public List<Stockout> getMostRecentStockoutsForFacility(
			String facilityCode, int limit);

	/**
	 * 
	 * @param provinceName
	 * @return
	 */
	public List<Stockout> getStockoutsForProvince(String provinceName);

	/**
	 * 
	 * @param districtName
	 * @return
	 */
	public List<Stockout> getStockoutsForDistrict(String districtName);

	/**
	 * 
	 * @param townName
	 * @return
	 */
	public List<Stockout> getStockoutsForTown(String townName);

	/**
	 * 
	 * @param medicine
	 * @return
	 */
	public List<Stockout> getStockoutsForMedicine(Medicine medicine);

	/**
	 * 
	 * @param medicineClass
	 * @return
	 */
	public List<Stockout> getStockoutsForMedicineClass(
			MedicineClass medicineClass);

	/**
	 * 
	 */
	public void updateAllStockoutPriorities();

}
