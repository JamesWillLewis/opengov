package za.org.opengov.common.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.StaffMemberDao;
import za.org.opengov.common.entity.StaffMember;

@Repository("staffMemberDao")
public class StaffMemberDaoImpl extends AbstractDaoImpl<StaffMember, Long> implements StaffMemberDao {

	public StaffMemberDaoImpl() {
		super(StaffMember.class);
	}

}
