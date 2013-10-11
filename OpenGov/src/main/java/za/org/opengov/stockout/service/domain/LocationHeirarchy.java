package za.org.opengov.stockout.service.domain;

import java.util.ArrayList;
import java.util.List;


public class LocationHeirarchy {
	
	private List<Province> provinces;
	
	public LocationHeirarchy() {
		this.provinces = new ArrayList<Province>();
	}
	
	public LocationHeirarchy(List<Province> provinces) {
		this.provinces = provinces;
	}
	
	public List<Province> getProvinces() {
		return provinces;
	}
	
	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}
	
	public void addProvince(Province province){
		this.provinces.add(province);
	}
	
	public Province findProvince(String provinceName){
		for(Province province: provinces){
			if(province.getName().equalsIgnoreCase(provinceName)){
				return province;
			}
		}
		return null;
	}

}
