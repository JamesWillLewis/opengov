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
package za.org.opengov.stockout.service.domain;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonView;

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
