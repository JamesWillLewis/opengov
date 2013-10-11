package za.org.opengov.stockout.service.domain;

import java.util.ArrayList;
import java.util.List;

public class District {
	
	private String name;
	private Province province;
	private List<Town> towns;
	
	public District(String name, Province province) {
		this.towns = new ArrayList<Town>();
		this.name = name;
		this.province = province;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Province getProvince() {
		return province;
	}
	
	public void setProvince(Province province) {
		this.province = province;
	}
	
	public List<Town> getTowns() {
		return towns;
	}
	
	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}
	
	public void addTown(Town town){
		this.towns.add(town);
	}
	
	public Town findTown(String townName){
		for(Town town: towns){
			if(town.getName().equalsIgnoreCase(townName)){
				return town;
			}
		}
		return null;
	}

}
