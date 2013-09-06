package za.org.opengov.common.domain.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.AssignmentDao;
import za.org.opengov.common.domain.entity.Assignment;

@Repository("assignmentDao")
public class AssignmentDaoImpl extends AbstractDaoImpl<Assignment, Long> implements AssignmentDao {

	public AssignmentDaoImpl() {
		super(Assignment.class);
	}

}
