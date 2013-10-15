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

import za.org.opengov.common.dao.AssignmentDao;
import za.org.opengov.common.entity.Assignment;
import za.org.opengov.common.service.AssignmentService;

@Service("assignmentService")
@Transactional
public class AssignmentServiceImpl extends AbstractServiceImpl<AssignmentDao, Assignment, Long> implements AssignmentService {

	@Autowired
	public AssignmentServiceImpl(AssignmentDao dao) {
		super(dao);
	}

}
