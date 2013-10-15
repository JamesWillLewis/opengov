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
