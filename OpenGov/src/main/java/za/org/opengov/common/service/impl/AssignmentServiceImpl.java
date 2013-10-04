package za.org.opengov.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.AssignmentDao;
import za.org.opengov.common.entity.Assignment;
import za.org.opengov.common.service.AssignmentService;

@Service("assignmentService")
@Transactional
public class AssignmentServiceImpl extends AbstractServiceImpl<AssignmentDao, Assignment, Long> implements AssignmentService {

	@Autowired
	public AssignmentServiceImpl(AssignmentDao dao) {
		super(dao);
	}

}
