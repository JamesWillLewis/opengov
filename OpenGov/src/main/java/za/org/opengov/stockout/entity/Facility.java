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

/**
 * Domain entity for Facility concept. Annotated to allow object-relational
 * mapping using JPA/Hibernate.
 * 
 * Represents a Facility, which could be a pharmacy, hospital, clinic, etc.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "SOWS_FACILITY")
public class Facility {

	/**
	 * Primary key.
	 */
	private String uid;
	/**
	 * Supply depot which provides facility with medical stock.
	 */
	private SupplyDepot supplyDepot;

	/**
	 * Official name as defined by the Department of Health. eg. 'Khayelitsha
	 * Site B'
	 */
	private String officialDOHName;

	/**
	 * Historical or local name, typically known to the general public instead
	 * of the official name. Used as an alias when searching is performed.
	 */
	private String localName;

	/**
	 * The town where the facility resides. eg. Khayelitsha.
	 */
	private String town;
	/**
	 * The district where the facility resides. eg. Cape Town.
	 */
	private String district;

	/**
	 * The province where the facility resides. eg. Western Cape.
	 */
	private String province;
	
	/**
	 * Type of facility (Clinic, Pharmacy, Hospital, etc)
	 */
	private FacilityType facilityType;
	
	/**
	 * Geographical longitudinal coordinates.
	 */
	private Double longitudeDecimalDegrees;
	
	/**
	 * Geographical latitudinal coordinates.
	 */
	private Double latitudeDecimalDegress;
	
	/**
	 * Contact number, such as telephone or cellphone.
	 */
	private String contactNumber;
	
	/**
	 * Email address for the facility.
	 */
	private String emailAddress;

	/**
	 * Stock-outs for this facility (both resolved and open)
	 */
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
