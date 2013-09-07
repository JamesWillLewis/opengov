package za.org.opengov.common.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.AssignmentDao;
import za.org.opengov.common.dao.IssueDao;
import za.org.opengov.common.entity.Assignment;
import za.org.opengov.common.entity.Issue;

@Repository("issueDao")
public class IssueDaoImpl extends AbstractDaoImpl<Issue, Long> implements IssueDao {

	public IssueDaoImpl() {
		super(Issue.class);
	}

}
