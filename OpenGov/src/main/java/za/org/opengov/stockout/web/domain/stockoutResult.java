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

public class stockoutResult {
	
	private String province;
	private String town;
	private String facility;
	private String medicineName;
	private String brandName;
	private String stockoutStatus;

	
	public String getProvince() {
		return province;
	}
	public String getTown() {
		return town;
	}
	public String getFacility() {
		return facility;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public String getBrandName() {
		return brandName;
	}
	public String getStockoutStatus() {
		return stockoutStatus;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public void setStockoutStatus(String stockoutStatus) {
		this.stockoutStatus = stockoutStatus;
	}

}
