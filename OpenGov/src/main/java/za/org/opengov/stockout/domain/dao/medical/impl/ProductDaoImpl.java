package za.org.opengov.stockout.domain.dao.medical.impl;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.medical.ProductDao;
import za.org.opengov.stockout.domain.entity.medical.Product;

public class ProductDaoImpl  extends AbstractDaoImpl<Product, Long> implements ProductDao {

	protected ProductDaoImpl(Class<Product> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
