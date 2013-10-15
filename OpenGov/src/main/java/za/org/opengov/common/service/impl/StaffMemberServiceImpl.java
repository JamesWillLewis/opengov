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
package za.org.opengov.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.StaffMemberDao;
import za.org.opengov.common.entity.StaffMember;
import za.org.opengov.common.service.StaffMemberService;

@Service("staffMemberService")
@Transactional
public class StaffMemberServiceImpl extends AbstractServiceImpl<StaffMemberDao, StaffMember, Long> implements StaffMemberService{

	@Autowired
	public StaffMemberServiceImpl(StaffMemberDao dao) {
		super(dao);
	}

}
