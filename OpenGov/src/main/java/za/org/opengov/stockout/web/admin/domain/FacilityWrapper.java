package za.org.opengov.stockout.web.admin.domain;

import za.org.opengov.stockout.entity.Facility;


public class FacilityWrapper {
	
	private Facility facility;
	
	public FacilityWrapper() {
	}
	
	public FacilityWrapper(Facility facility) {
		this.facility = facility;
	}
	
	public Facility getFacility() {
		return facility;
	}
	
	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}
