package za.org.opengov.stockout.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.medical.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDaoImpl<Product, Long> implements
		ProductDao {

	protected ProductDaoImpl() {
		super(Product.class);
	}

}
