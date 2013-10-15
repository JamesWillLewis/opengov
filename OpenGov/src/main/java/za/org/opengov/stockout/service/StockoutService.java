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
	 * All stockouts which occured at the specified facility UID. 
	 * 
	 * @param facilityCode Facility UID/Code
	 * @return List of stockouts
	 */
	public List<Stockout> getAllStockoutsForFacility(String facilityCode);

	/**
	 * 
	 * @param productCode
	 * @return
	 */
	public List<Stockout> getAllStockoutsForProduct(String productCode);

	/**
	 * 
	 * @param facilityCode
	 * @param productCode
	 * @return
	 */
	public Stockout getStockout(String facilityCode, String productCode);

	/**
	 * 
	 * @param facilityCode
	 * @return
	 */
	public Stockout getMostCommonlyReportedStockoutForFacility(
			String facilityCode);

	/**
	 * 
	 * @param facilityCode
	 * @param limit
	 * @return
	 */
	public List<Stockout> getMostCommonlyReportedStockoutsForFacility(
			String facilityCode, int limit);

	/**
	 * 
	 * @return
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
	public List<Stockout> getStockoutsForMedicineClass(MedicineClass medicineClass);

	/**
	 * 
	 */
	public void updateAllStockoutPriorities();

}
