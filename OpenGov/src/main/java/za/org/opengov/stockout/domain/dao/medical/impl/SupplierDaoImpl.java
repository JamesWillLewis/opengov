package za.org.opengov.stockout.domain.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.medical.SupplierDao;
import za.org.opengov.stockout.domain.entity.medical.Supplier;

@Repository("supplierDao")
public class SupplierDaoImpl extends AbstractDaoImpl<Supplier, Long> implements
		SupplierDao {

	protected SupplierDaoImpl() {
		super(Supplier.class);
	}

}
