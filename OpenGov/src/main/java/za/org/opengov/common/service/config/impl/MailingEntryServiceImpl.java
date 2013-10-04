package za.org.opengov.common.service.config.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.org.opengov.common.dao.config.MailingEntryDao;
import za.org.opengov.common.entity.config.MailingEntry;
import za.org.opengov.common.service.config.MailingEntryService;
import za.org.opengov.common.service.impl.AbstractServiceImpl;

@Service
public class MailingEntryServiceImpl extends AbstractServiceImpl<MailingEntryDao, MailingEntry, String> 
implements MailingEntryService{

	@Autowired
	public MailingEntryServiceImpl(MailingEntryDao dao) {
		super(dao);
	}

	@Override
	public List<MailingEntry> getAllMailingEntriesForRole(String roleTag) {
		return dao.findAllForRole(roleTag);
	}

}
