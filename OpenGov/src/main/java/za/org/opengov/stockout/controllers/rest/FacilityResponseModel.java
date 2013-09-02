package za.org.opengov.stockout.controllers.rest;


public class FacilityResponseModel {
	
	private String facilityName;
	private String code;
	private String region;
	
	public FacilityResponseModel() {
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	

	public String getCode() {
		return code;
	}
	
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	

	public String getFacilityName() {
		return facilityName;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	

	public String getRegion() {
		return region;
	}

}
