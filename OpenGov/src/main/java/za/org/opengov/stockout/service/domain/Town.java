package za.org.opengov.stockout.service.domain;

import java.util.LinkedList;
import java.util.List;

import za.org.opengov.stockout.entity.Facility;

public class Town {

	private String name;
	private District district;
	private List<Facility> facilities;

	public Town(String name, District district) {
		this.facilities = new LinkedList<Facility>();
		this.name = name;
		this.district = district;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}

	public void addFacility(Facility facility) {
		this.facilities.add(facility);
	}

}
