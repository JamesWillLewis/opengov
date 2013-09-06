package za.org.opengov.stockout.domain.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.SubjectDao;
import za.org.opengov.stockout.domain.entity.Subject;

@Repository("subjectDao")
public class SubjectDaoImpl extends AbstractDaoImpl<Subject, Long> implements SubjectDao {

	protected SubjectDaoImpl() {
		super(Subject.class);
	}

}
