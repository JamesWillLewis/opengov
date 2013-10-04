package za.org.opengov.common.dao.config;

import java.util.List;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.common.entity.config.MailingEntry;

public interface MailingEntryDao extends AbstractDao<MailingEntry, String> {

	public List<MailingEntry> findAllForRole(String roleTag);

}
