package za.org.opengov.common.service.config;

import java.util.List;

import za.org.opengov.common.entity.config.MailingEntry;
import za.org.opengov.common.service.AbstractService;

public interface MailingEntryService extends AbstractService<MailingEntry, String>{
	
	public List<MailingEntry> getAllMailingEntriesForRole(String roleTag);

}
