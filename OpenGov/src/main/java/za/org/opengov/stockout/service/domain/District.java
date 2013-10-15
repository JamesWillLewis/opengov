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

import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonRootName;

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
