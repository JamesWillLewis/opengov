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

import java.util.Date;

import za.org.opengov.stockout.entity.Stockout;


/**This class is used to store information to load up the search results table on the stockout home page.
 * Variables correspond to columns in the table and each stockoutresult class represents one stockout search result*/
public class StockoutResult {

	private String province;
	private String town;
	private String facility;
	private String medicineClass;
	private String dateOfFirstIssue;
	private String medicineName;
	private String brandName;
	private String stockoutStatus;

	public StockoutResult() {

	}

	public StockoutResult(Stockout stockout) {

		this.province = stockout.getFacility().getProvince();
		this.town = stockout.getFacility().getTown();
		this.facility = stockout.getFacility().getLocalName() + " "
				+ stockout.getFacility().getFacilityType().getReadable();
		this.medicineClass = stockout.getProduct().getMedicine().getMedicineClass().getUid();
		this.medicineName = stockout.getProduct().getMedicine().getName();
		this.brandName = stockout.getProduct().getName() + " " + stockout.getProduct().getDescription();
		this.dateOfFirstIssue = stockout.getIssue().getStartTimestamp().toString();
		this.stockoutStatus = stockout.getIssue().getState().toString();

	}

	public String getMedicineClass() {
		return medicineClass;
	}

	public String getDateOfFirstIssue() {
		return dateOfFirstIssue;
	}

	public void setMedicineClass(String medicineClass) {
		this.medicineClass = medicineClass;
	}

	public void setDateOfFirstIssue(String dateOfFirstIssue) {
		this.dateOfFirstIssue = dateOfFirstIssue;
	}

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
