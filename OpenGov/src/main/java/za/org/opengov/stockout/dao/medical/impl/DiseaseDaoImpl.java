package za.org.opengov.stockout.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.DiseaseDao;
import za.org.opengov.stockout.entity.medical.Disease;

@Repository("diseaseDao")
public class DiseaseDaoImpl extends AbstractDaoImpl<Disease, Long> implements DiseaseDao {

	protected DiseaseDaoImpl() {
		super(Disease.class);
	}

}
