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
package za.org.opengov.common.dao.config.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.config.MailingEntryDao;
import za.org.opengov.common.dao.config.MailingRoleDao;
import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.common.entity.config.MailingRole;

/**
 * Concrete implementation of {@link MailingRoleDao} DAO.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Repository
public class MailingRoleDaoImpl extends AbstractDaoImpl<MailingRole, Long> implements MailingRoleDao {

	protected MailingRoleDaoImpl() {
		super(MailingRole.class);
	}

}
