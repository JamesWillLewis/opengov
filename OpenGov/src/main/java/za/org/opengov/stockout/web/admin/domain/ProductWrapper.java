package za.org.opengov.stockout.web.admin.domain;

import za.org.opengov.stockout.entity.medical.Product;


public class ProductWrapper {
	
	private Product product;
	private String displayName;
	
	
	public ProductWrapper() {
	}
	
	public ProductWrapper(Product product) {
		this.product = product;
		this.displayName = product.getName() + " - " + product.getDescription();
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
