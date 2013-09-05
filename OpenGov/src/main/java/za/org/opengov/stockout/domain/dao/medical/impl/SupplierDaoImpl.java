package za.org.opengov.stockout.domain.dao.medical.impl;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.medical.SupplierDao;
import za.org.opengov.stockout.domain.entity.medical.Supplier;

public class SupplierDaoImpl extends AbstractDaoImpl<Supplier, Long> implements SupplierDao {

	protected SupplierDaoImpl(Class<Supplier> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
