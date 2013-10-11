package za.org.opengov.stockout.service.domain;

import java.util.LinkedList;
import java.util.List;

public class Province {
	
	private String name;
	private List<District> districts;
	private LocationHeirarchy locationHeirarchy;
	
	public Province(String name, LocationHeirarchy locationHeirarchy) {
		this.name = name;
		this.districts = new LinkedList<District>();
		this.locationHeirarchy = locationHeirarchy;
	}
	
	public LocationHeirarchy getLocationHeirarchy() {
		return locationHeirarchy;
	}
	
	public void setLocationHeirarchy(LocationHeirarchy locationHeirarchy) {
		this.locationHeirarchy = locationHeirarchy;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<District> getDistricts() {
		return districts;
	}
	
	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}
	
	public void addDistrict(District district){
		this.districts.add(district);
	}
	
	public District findDistrict(String districtName){
		for(District district: districts){
			if(district.getName().equalsIgnoreCase(districtName)){
				return district;
			}
		}
		return null;
	}
	
}
