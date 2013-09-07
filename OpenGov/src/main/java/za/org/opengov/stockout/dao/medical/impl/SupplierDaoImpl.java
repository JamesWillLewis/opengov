package za.org.opengov.stockout.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.SupplierDao;
import za.org.opengov.stockout.entity.medical.Supplier;

@Repository("supplierDao")
public class SupplierDaoImpl extends AbstractDaoImpl<Supplier, Long> implements
		SupplierDao {

	protected SupplierDaoImpl() {
		super(Supplier.class);
	}

}
