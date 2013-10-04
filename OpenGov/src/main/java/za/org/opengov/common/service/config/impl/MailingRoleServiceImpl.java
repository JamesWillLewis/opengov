package za.org.opengov.common.service.config.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.org.opengov.common.dao.config.MailingRoleDao;
import za.org.opengov.common.entity.config.MailingRole;
import za.org.opengov.common.service.config.MailingRoleService;
import za.org.opengov.common.service.impl.AbstractServiceImpl;

@Service
public class MailingRoleServiceImpl extends
		AbstractServiceImpl<MailingRoleDao, MailingRole, Long> implements
		MailingRoleService {

	@Autowired
	public MailingRoleServiceImpl(MailingRoleDao dao) {
		super(dao);
	}

}
