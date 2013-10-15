/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
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
