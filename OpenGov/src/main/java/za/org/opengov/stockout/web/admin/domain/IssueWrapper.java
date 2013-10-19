package za.org.opengov.stockout.web.admin.domain;

import za.org.opengov.common.entity.Issue;
import za.org.opengov.common.entity.IssueState;

public class IssueWrapper {

	private IssueState issueState;
	
	public IssueWrapper(){
		
	}
	
	public IssueWrapper(Issue issue){
		
		this.issueState = issue.getState();
	}

	public IssueState getIssueState() {
		return issueState;
	}

	public void setIssueState(IssueState issueState) {
		this.issueState = issueState;
	}
	
}
