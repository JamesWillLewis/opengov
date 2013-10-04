package za.org.opengov.stockout.service.medical.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.medical.SupplierDao;
import za.org.opengov.stockout.entity.medical.Supplier;
import za.org.opengov.stockout.service.medical.SupplierService;

@Service("supplierService")
@Transactional
public class SupplierServiceImpl extends AbstractServiceImpl<SupplierDao, Supplier, Long> implements SupplierService {

	@Autowired
	public SupplierServiceImpl(SupplierDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

}
