package za.org.opengov.stockout.web.admin.domain;

import za.org.opengov.common.entity.IssueState;
import za.org.opengov.stockout.entity.Stockout;

/**Wraps data for/from the Stockout table for client side display*/
public class StockoutWrapper {

	private Stockout stockout;
	
	private String facilityUID;
	
	private String productUID;
	
	private IssueState issueState;
	
	public StockoutWrapper() {
	}

	public StockoutWrapper(Stockout stockout) {
		this.stockout = stockout;
		this.facilityUID = stockout.getFacility().getUid();
		this.productUID = stockout.getProduct().getUid();
		this.issueState = stockout.getIssue().getState();
	}

		
	public Stockout getStockout() {
		return stockout;
	}
	

	public IssueState getIssueState() {
		return issueState;
	}

	public void setIssueState(IssueState issueState) {
		this.issueState = issueState;
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
