package za.org.opengov.stockout.domain.dao.medical.impl;

import za.org.opengov.common.domain.dao.AssignmentDao;
import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.common.domain.entity.Assignment;
import za.org.opengov.stockout.domain.dao.medical.DiseaseDao;
import za.org.opengov.stockout.domain.entity.medical.Disease;

public class DiseaseDaoImpl extends AbstractDaoImpl<Disease, Long> implements DiseaseDao {

	protected DiseaseDaoImpl(Class<Disease> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
