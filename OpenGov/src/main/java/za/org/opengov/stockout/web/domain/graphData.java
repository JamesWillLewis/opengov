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
package za.org.opengov.stockout.web.domain;

import java.util.List;

import za.org.opengov.stockout.entity.Stockout;

/**GraphData Entity class specifies various data for graphs to use**/
public class graphData {
	
	private List<Long> locationStockouts;
	private List<String> locations;
	private List<String> medicines;
	private List<Long> medicineStockouts;
	private List<stockoutResult> allStockouts;
	private List<MapMarker> markers;
	
	public List<MapMarker> getMarkers() {
		return markers;
	}
	public void setMarkers(List<MapMarker> markers) {
		this.markers = markers;
	}
	public List<stockoutResult> getAllStockouts() {
		return allStockouts;
	}
	public void setAllStockouts(List<stockoutResult> allStockouts) {
		this.allStockouts = allStockouts;
	}
	public List<Long> getLocationStockouts() {
		return locationStockouts;
	}
	public List<String> getLocations() {
		return locations;
	}
	public List<String> getMedicines() {
		return medicines;
	}
	public List<Long> getMedicineStockouts() {
		return medicineStockouts;
	}
	public void setLocationStockouts(List<Long> locationStockouts) {
		this.locationStockouts = locationStockouts;
	}
	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
	public void setMedicines(List<String> medicines) {
		this.medicines = medicines;
	}
	public void setMedicineStockouts(List<Long> medicineStockouts) {
		this.medicineStockouts = medicineStockouts;
	}
	

}
