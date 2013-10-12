package za.org.opengov.stockout.service;

import java.io.File;
import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.FacilityType;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.domain.LocationHeirarchy;

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
