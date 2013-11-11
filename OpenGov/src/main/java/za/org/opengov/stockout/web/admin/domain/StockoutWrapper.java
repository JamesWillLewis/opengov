package za.org.opengov.stockout.web.admin.domain;

import za.org.opengov.stockout.entity.Stockout;

/**Wraps data for/from the Stockout table for client side display*/
public class StockoutWrapper {

	private Stockout stockout;
	
	private String facilityUID;
	
	private String productUID;
	
	public StockoutWrapper() {
	}

	public StockoutWrapper(Stockout stockout) {
		this.stockout = stockout;
		this.facilityUID = stockout.getFacility().getUid();
		this.productUID = stockout.getProduct().getUid();
	}

	public Stockout getStockout() {
		return stockout;
	}

	public void setStockout(Stockout stockout) {
		this.stockout = stockout;
	}
	
	public String getFacilityUID() {
		return facilityUID;
	}
	
	public void setFacilityUID(String facilityUID) {
		this.facilityUID = facilityUID;
	}
	
	public String getProductUID() {
		return productUID;
	}
	
	public void setProductUID(String productUID) {
		this.productUID = productUID;
	}

}
