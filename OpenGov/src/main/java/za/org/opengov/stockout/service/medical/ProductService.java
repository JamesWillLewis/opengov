package za.org.opengov.stockout.service.medical;

import java.util.List;

import za.org.opengov.stockout.entity.medical.Product;

public interface ProductService {
	
	public void saveProduct(Product product);

	public List<Product> getProductsInAlphabeticRange();
	
	
	/**
	 * Performs string closeness matching and returns the nearest matching
	 * product to the specified product name. 
	 * 
	 * @param productName
	 * @return
	 */
	public Product getClosestMatch(String productName);
	
}
