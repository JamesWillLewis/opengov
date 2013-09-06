package za.org.opengov.stockout.domain.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.medical.DiseaseDao;
import za.org.opengov.stockout.domain.entity.medical.Disease;

@Repository("diseaseDao")
public class DiseaseDaoImpl extends AbstractDaoImpl<Disease, Long> implements DiseaseDao {

	protected DiseaseDaoImpl() {
		super(Disease.class);
	}

}
