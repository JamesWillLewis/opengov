package za.org.opengov.stockout.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SOWS_FACILITY")
public class Facility {

	private String uid;
	private SupplyDepot supplyDepot;
	private String officialDOHName;
	private String localName;
	private String town;
	private String district;
	private String province;
	private FacilityType facilityType;
	// geographic
	private Double longitudeDecimalDegrees;
	private Double latitudeDecimalDegress;
	//contact
	private String contactNumber;
	private String emailAddress;

	private Set<Stockout> stockouts = new HashSet<Stockout>();

	public Facility() {
	}

	@Id
	@Column(name = "UID", unique = true, nullable = false)
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@ManyToOne
	@JoinColumn(name = "FK_SUPPLY_DEPOT")
	public SupplyDepot getSupplyDepot() {
		return supplyDepot;
	}

	public void setSupplyDepot(SupplyDepot supplyDepot) {
		this.supplyDepot = supplyDepot;
	}

	@Column(name = "OFFICIAL_DOH_NAME")
	public String getOfficialDOHName() {
		return officialDOHName;
	}

	public void setOfficialDOHName(String officialDOHName) {
		this.officialDOHName = officialDOHName;
	}

	@Column(name = "LOCAL_NAME")
	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	@Column(name = "TOWN")
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Column(name = "DISTRICT")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "FACILITY_TYPE")
	@Enumerated(EnumType.STRING)
	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	@Column(name = "LATITUDE")
	public Double getLatitudeDecimalDegress() {
		return latitudeDecimalDegress;
	}

	public void setLatitudeDecimalDegress(Double latitudeDecimalDegress) {
		this.latitudeDecimalDegress = latitudeDecimalDegress;
	}

	@Column(name = "LONGITUDE")
	public Double getLongitudeDecimalDegrees() {
		return longitudeDecimalDegrees;
	}

	public void setLongitudeDecimalDegrees(Double longitudeDecimalDegrees) {
		this.longitudeDecimalDegrees = longitudeDecimalDegrees;
	}

	@OneToMany(mappedBy = "facility")
	public Set<Stockout> getStockouts() {
		return stockouts;
	}

	public void setStockouts(Set<Stockout> stockouts) {
		this.stockouts = stockouts;
	}

	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CONTACT_NUMBER", length = 10)
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name = "EMAIL_ADDRESS", length = 10)
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
