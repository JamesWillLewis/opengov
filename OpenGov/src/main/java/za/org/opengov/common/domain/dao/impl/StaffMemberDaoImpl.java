package za.org.opengov.common.domain.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.StaffMemberDao;
import za.org.opengov.common.domain.entity.StaffMember;

@Repository("staffMemberDao")
public class StaffMemberDaoImpl extends AbstractDaoImpl<StaffMember, Long> implements StaffMemberDao {

	public StaffMemberDaoImpl() {
		super(StaffMember.class);
	}

}
