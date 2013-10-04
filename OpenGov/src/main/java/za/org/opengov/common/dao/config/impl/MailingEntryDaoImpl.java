package za.org.opengov.common.dao.config.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.config.MailingEntryDao;
import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.common.entity.config.MailingEntry;

@Repository
public class MailingEntryDaoImpl extends AbstractDaoImpl<MailingEntry, String>
		implements MailingEntryDao {

	protected MailingEntryDaoImpl() {
		super(MailingEntry.class);
	}

	@Override
	public List<MailingEntry> findAllForRole(String roleTag) {

		String query = "select distinct e from MailingEntry e inner join e.mailingRoles m where m.roleID like :roleTag";

		return getCurrentSession().createQuery(query)
				.setString("roleTag", roleTag).list();
	}

}
