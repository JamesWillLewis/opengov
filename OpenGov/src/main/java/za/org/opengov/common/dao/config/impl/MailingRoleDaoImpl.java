package za.org.opengov.common.dao.config.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.config.MailingRoleDao;
import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.common.entity.config.MailingRole;

@Repository
public class MailingRoleDaoImpl extends AbstractDaoImpl<MailingRole, Long> implements MailingRoleDao {

	protected MailingRoleDaoImpl() {
		super(MailingRole.class);
	}

}
