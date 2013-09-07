package za.org.opengov.stockout.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.DosageDao;
import za.org.opengov.stockout.entity.medical.Dosage;

@Repository("dosageDao")
public class DosageDaoImpl extends AbstractDaoImpl<Dosage, Long> implements DosageDao {

	protected DosageDaoImpl() {
		super(Dosage.class);
	}

}
