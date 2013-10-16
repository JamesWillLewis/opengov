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

	public void saveFacility(Facility facility);

	public Facility getClosestMatch(String facilityIdentifier);

	public List<Facility> getAllFacilitiesWithStock(Product product);

	public Facility getNearestFacilityWithStock(Product product,
			Facility originFacility);

	public void populateDatabaseFromCSV(File file, String seperator,
			String textDelimeter, FacilityType facilityType);
	
	public String generateFacilityCode(String name);
	
	
	public List<String> listAllProvinces();
	
	public List<String> listAllDistrictsForProvince(String provinceName);
	
	public List<String> listAllTownsForDistrict(String districtName);
	
	public List<Facility> listAllFacilitiesForTown(String townName);
	
	public long totalStockoutsForProvince(String provinceName);
	
	public long totalStockoutsForDistrict(String districtName);
	
	public long totalStockoutsForTown(String townName);

	public LocationHeirarchy getLocationHeirarchy();
	
	public List<Facility> listAllFacilitiesForProvince(String provinceName);
	
}
