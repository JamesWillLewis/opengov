package za.org.opengov.stockout.service.medical;

import java.io.File;
import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.medical.Product;

public interface ProductService extends AbstractService<Product, String> {

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

	public void populateDatabaseFromCSV(File file, String seperator,
			String textDelimeter);

}
