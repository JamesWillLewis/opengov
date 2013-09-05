package za.org.opengov.stockout.domain.dao.medical.impl;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.medical.DosageDao;
import za.org.opengov.stockout.domain.entity.medical.Dosage;

public class DosageDaoImpl extends AbstractDaoImpl<Dosage, Long> implements DosageDao {

	protected DosageDaoImpl(Class<Dosage> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
