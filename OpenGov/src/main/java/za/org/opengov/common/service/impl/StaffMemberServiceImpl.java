package za.org.opengov.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.StaffMemberDao;
import za.org.opengov.common.entity.StaffMember;
import za.org.opengov.common.service.StaffMemberService;

@Service("staffMemberService")
@Transactional
public class StaffMemberServiceImpl extends AbstractServiceImpl<StaffMemberDao, StaffMember, Long> implements StaffMemberService{

	@Autowired
	public StaffMemberServiceImpl(StaffMemberDao dao) {
		super(dao);
	}

}
