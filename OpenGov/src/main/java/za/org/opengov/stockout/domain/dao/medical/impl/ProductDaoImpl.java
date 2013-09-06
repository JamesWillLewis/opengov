package za.org.opengov.stockout.domain.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.medical.ProductDao;
import za.org.opengov.stockout.domain.entity.medical.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDaoImpl<Product, Long> implements
		ProductDao {

	protected ProductDaoImpl() {
		super(Product.class);
	}

}
