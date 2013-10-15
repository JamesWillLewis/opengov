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
package za.org.opengov.common.dao.config;

import java.util.List;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.common.entity.config.MailingEntry;

/**
 * Data Access Object for {@link MailingEntry}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface MailingEntryDao extends AbstractDao<MailingEntry, String> {

	/**
	 * Return a list of mailing entries for a specified role.
	 * 
	 * @param roleTag
	 *            Mailing role
	 * @return List of mailing entries for role.
	 */
	public List<MailingEntry> findAllForRole(String roleTag);

}
