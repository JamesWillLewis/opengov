package za.org.opengov.stockout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "SOWS_FACILITY")
public class Facility {

	private String uid;
	private SupplyDepot supplyDepot;
	private String officialDOHName;
	private String localName;
	private String municipality;
	private String region;
	private FacilityType facilityType;
	
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

	@Column(name = "MUNICIPALITY")
	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	@Column(name = "REGION")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "FACILITY_TYPE")
	@Enumerated(EnumType.STRING)
	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

}
