package za.org.opengov.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.IssueDao;
import za.org.opengov.common.entity.Issue;
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
				/ (double) ((occMax - occMin) == 0 ? 1 : occMax
						- occMin);
		double normDur = (double) (duration - durMin)
				/ (double) ((durMax - durMin) == 0 ? 1 : durMax - durMin);
		;
		
		double factor = Math.sqrt(Math.pow(normSev, 2) + Math.pow(normOcc, 2)
				+ Math.pow(normDur, 2));

		return (int) ((factor / Math.sqrt(3)) * 100);
	}

}
