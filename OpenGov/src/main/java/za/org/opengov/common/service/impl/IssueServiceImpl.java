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

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.IssueDao;
import za.org.opengov.common.entity.Issue;
import za.org.opengov.common.entity.IssueState;
import za.org.opengov.common.service.IssueService;

@Service("issueService")
@Transactional
public class IssueServiceImpl extends
		AbstractServiceImpl<IssueDao, Issue, Long> implements IssueService {

	@Autowired
	public IssueServiceImpl(IssueDao dao) {
		super(dao);
	}

	@Override
	public int calculatePriority(int severity, int occurance, int duration,
			int sevMin, int occMin, int durMin, int sevMax, int occMax,
			int durMax) {

		// normalize
		double normSev = (double) (severity - sevMin)
				/ (double) ((sevMax - sevMin) == 0 ? 1 : sevMax - sevMin);
		double normOcc = (double) (occurance - occMin)
				/ (double) ((occMax - occMin) == 0 ? 1 : occMax - occMin);
		double normDur = (double) (duration - durMin)
				/ (double) ((durMax - durMin) == 0 ? 1 : durMax - durMin);
		;

		double factor = Math.sqrt(Math.pow(normSev, 2) + Math.pow(normOcc, 2)
				+ Math.pow(normDur, 2));

		return (int) ((factor / Math.sqrt(3)) * 100);
	}

	@Override
	public void updateIssueState(Issue issue, IssueState state) {
		issue.setState(state);
		if (state == IssueState.CLOSED) {
			issue.setEndTimestamp(Calendar.getInstance().getTime());
		} else if (state == IssueState.OPEN) {
			issue.setStartTimestamp(Calendar.getInstance().getTime());
		}

		put(issue);
	}

}
