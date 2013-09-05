package za.org.opengov.common.domain.dao.impl;

import za.org.opengov.common.domain.dao.AssignmentDao;
import za.org.opengov.common.domain.dao.IssueDao;
import za.org.opengov.common.domain.entity.Assignment;
import za.org.opengov.common.domain.entity.Issue;

public class IssueDaoImpl extends AbstractDaoImpl<Issue, Long> implements IssueDao {

	protected IssueDaoImpl(Class<Issue> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
