package za.org.opengov.stockout.web.admin.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.FacilityType;

/**Wraps data for/from the Facilities table for client side display*/
public class FacilityWrapper {
	
	
	private String localName;
	
	
	private String officialName;
	
	
	private String province;
	

	private String district;
	
	
	private String town;
	
	
	private FacilityType type;
	

	private String contactNumber;
	

	private String emailAddress;
	
	private double latitude;
	
	private double longitude;
	
	private String uid;
	


	public FacilityWrapper() {
	}
	
	public FacilityWrapper(Facility facility){
		
		this.localName = facility.getLocalName();
		this.officialName = facility.getOfficialDOHName();
		this.contactNumber = facility.getContactNumber();
		this.emailAddress = facility.getEmailAddress();
		this.type = facility.getFacilityType();
		this.province = facility.getProvince();
		this.town = facility.getTown();
		this.district = facility.getDistrict();
		if(facility.getLatitudeDecimalDegress() != null && facility.getLongitudeDecimalDegrees()!=null){
		this.latitude = facility.getLatitudeDecimalDegress();
		this.longitude = facility.getLongitudeDecimalDegrees();}
		else{
			this.latitude =0;
			this.longitude=0;
		}
		this.uid = facility.getUid();
	}


	
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLatitude(double latititude) {
		this.latitude = latititude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getLocalName() {
		return localName;
	}


	public String getOfficialName() {
		return officialName;
	}


	public String getProvince() {
		return province;
	}


	public String getDistrict() {
		return district;
	}


	public String getTown() {
		return town;
	}


	public FacilityType getType() {
		return type;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setLocalName(String localName) {
		this.localName = localName;
	}


	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public void setTown(String town) {
		this.town = town;
	}


	public void setType(FacilityType type) {
		this.type = type;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
}
