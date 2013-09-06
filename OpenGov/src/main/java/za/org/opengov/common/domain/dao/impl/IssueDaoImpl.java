package za.org.opengov.common.domain.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.AssignmentDao;
import za.org.opengov.common.domain.dao.IssueDao;
import za.org.opengov.common.domain.entity.Assignment;
import za.org.opengov.common.domain.entity.Issue;

@Repository("issueDao")
public class IssueDaoImpl extends AbstractDaoImpl<Issue, Long> implements IssueDao {

	public IssueDaoImpl() {
		super(Issue.class);
	}

}
