package za.org.opengov.common.service;

import za.org.opengov.common.entity.Issue;

public interface IssueService extends AbstractService<Issue, Long> {

	public int calculatePriority(int severity, int occurance, int duration,
			int sevMin, int occMin, int durMin, int sevMax, int occMax,
			int durMax);

}
