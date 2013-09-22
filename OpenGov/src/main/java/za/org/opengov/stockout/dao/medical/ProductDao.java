package za.org.opengov.stockout.dao.medical;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.stockout.entity.medical.Product;

public interface ProductDao  extends AbstractDao<Product, String> {

	public Product getMostCommonStockoutForFacility(String facilityCode);
	
}
