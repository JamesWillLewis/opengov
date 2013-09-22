package za.org.opengov.stockout.service.medical;

import java.util.List;

import za.org.opengov.stockout.entity.medical.Product;

public interface ProductService {
	

	public List<Product> getProductsInAlphabeticRange();
	
	public Product getMostCommonStockoutForFacility(String facilityCode);
	
	/**
	 * Performs string closeness matching and returns the nearest matching
	 * product to the specified product name. 
	 * 
	 * @param productName
	 * @return
	 */
	public Product getClosestMatch(String productName);
	
}
