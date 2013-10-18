package za.org.opengov.stockout.web.admin.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.FacilityType;


public class FacilityWrapper {
	
	@NotEmpty
	private String localName;
	
	@NotEmpty
	private String officialName;
	
	@NotEmpty
	private String province;
	
	@NotEmpty
	private String district;
	
	@NotEmpty
	private String town;
	
	@NotEmpty
	private FacilityType type;
	
	@NotNull @Min(10) @Max(12)
	private String contactNumber;
	
	@Email @NotEmpty
	private String emailAddress;
	
	
	public FacilityWrapper() {
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
