package za.org.opengov.stockout.domain.dao.impl;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.SubjectDao;
import za.org.opengov.stockout.domain.entity.Subject;

public class SubjectDaoImpl extends AbstractDaoImpl<Subject, Long> implements SubjectDao {

	protected SubjectDaoImpl(Class<Subject> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
