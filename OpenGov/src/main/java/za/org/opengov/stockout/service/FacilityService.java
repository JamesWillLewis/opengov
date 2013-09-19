package za.org.opengov.stockout.service;

import za.org.opengov.stockout.entity.Facility;

public interface FacilityService {

	/**
	 * Validates the existence of the specified facility, as uniquely
	 * identified by the facility code. Will return the facility entity object,
	 * or null if the facility does not exist. 
	 * 
	 * @param facilityCode Facility UID primary key.
	 * @return Facility instance for the specified UID, or null if facility does not exist.
	 */
	public Facility validateFacilityCode(String facilityCode);
	
	public void saveFacility(Facility facility);
	
}
