package za.org.opengov.stockout.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.SubjectDao;
import za.org.opengov.stockout.entity.Subject;
import za.org.opengov.stockout.service.SubjectService;

@Service("subjectService")
@Transactional
public class SubjectServiceImpl extends AbstractServiceImpl<SubjectDao, Subject, Long> implements SubjectService {

	@Autowired
	public SubjectServiceImpl(SubjectDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

}
