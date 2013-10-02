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
