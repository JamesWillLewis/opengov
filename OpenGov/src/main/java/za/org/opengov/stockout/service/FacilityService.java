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

import java.io.File;
import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.FacilityType;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.domain.LocationHeirarchy;

/**
 * Service (business object) class for {@link Facility}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface FacilityService extends AbstractService<Facility, String> {

	/**
	 * Validates the existence of the specified facility, as uniquely identified
	 * by the facility code. Will return the facility entity object, or null if
	 * the facility does not exist.
	 * 
	 * @param facilityCode
	 *            Facility UID primary key.
	 * @return Facility instance for the specified UID, or null if facility does
	 *         not exist.
	 */
	public Facility validateFacilityCode(String facilityCode);

	/**
	 * Create/update the facility.
	 * 
	 * @param facility
	 *            facility to create/update
	 */
	@Deprecated
	public void saveFacility(Facility facility);

	/**
	 * Find facility whose official or local name most closely matches the
	 * provided name. Matching is performed using a string-closeness algorithm.
	 * 
	 * @param facilityIdentifier
	 *            Official or local name of the facility, or the facility code.
	 * @return Closest matching facility.
	 */
	public Facility getClosestMatch(String facilityIdentifier);

	/**
	 * Find all facilities which do <b>not</b> have a stock-out of the specified
	 * product.
	 * 
	 * @param product
	 *            product to search against.
	 * @return Facilities which have no stock-out of given product.
	 */
	public List<Facility> getAllFacilitiesWithStock(Product product);

	/**
	 * Find nearest facility (using geographical coordinates) which does not
	 * have a stock-out of the given product.
	 * 
	 * @param product
	 *            Product to search against.
	 * @param originFacility
	 *            Facility to use as the 'current' location.
	 * @return Facility which has no reported stock-out of given product, as is
	 *         geographically closest to the given origin facility.
	 */
	public Facility getNearestFacilityWithStock(Product product,
			Facility originFacility);

	/**
	 * Perform back-fill of the database facility table from a CSV file.
	 * 
	 * @param file
	 *            CSV file of facility entries.
	 * @param seperator
	 *            Separator token used in CSV file.
	 * @param textDelimeter
	 *            Delimiter token used in CSV file.
	 * @param facilityType
	 *            Type of facility
	 */
	public void populateDatabaseFromCSV(File file, String seperator,
			String textDelimeter, FacilityType facilityType);

	/**
	 * Generate facility code from a facility name.
	 * 
	 * @param name
	 *            Name of facility.
	 * @return Facility code.
	 */
	public String generateFacilityCode(String name);

	/**
	 * Return list of provinces.
	 * 
	 * @return List of province name.
	 */
	public List<String> listAllProvinces();

	/**
	 * Find all districts for the given province.
	 * 
	 * @param provinceName
	 *            Province name.
	 * @return List of district names.
	 */
	public List<String> listAllDistrictsForProvince(String provinceName);

	/**
	 * Find all towns for given district.
	 * 
	 * @param districtName
	 *            District name.
	 * @return List of town names.
	 */
	public List<String> listAllTownsForDistrict(String districtName);

	/**
	 * Find all facilities for given town.
	 * 
	 * @param townName
	 *            Town name.
	 * @return List of facilities.
	 */
	public List<Facility> listAllFacilitiesForTown(String townName);

	/**
	 * @param provinceName
	 *            Province name.
	 * @return Total number of stock-outs for the province.
	 */
	public long totalStockoutsForProvince(String provinceName);

	/**
	 * 
	 * @param districtName District name.
	 * @return Total number of stock-outs for the district. 
	 */
	public long totalStockoutsForDistrict(String districtName);

	/**
	 * 
	 * @param townName Town name.
	 * @return Total number of stock-outs for the town.
	 */
	public long totalStockoutsForTown(String townName);

	/**
	 * Generates and returns a full location hierarchy, as
	 * determined from all the facilities and the provinces, districts, and towns
	 * where the facilities reside. 
	 * The hierarchy is in the order: province > district > town > facility.
	 * 
	 * @return Complete location hierarchy.
	 */
	public LocationHeirarchy getLocationHeirarchy();

	/**
	 * Find all facilities in a particular province.
	 * 
	 * @param provinceName Province name.
	 * @return List of facilities. 
	 */
	public List<Facility> listAllFacilitiesForProvince(String provinceName);

}
