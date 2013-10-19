package za.org.opengov.stockout.web.admin.domain;

import za.org.opengov.common.entity.Assignment;

public class AssignmentWrapper {
	
	private Long issueUID;
	
	private Long staffMemberUID;
	
	public AssignmentWrapper(){
		
	}
	
	public AssignmentWrapper(Assignment assign){
		
		this.staffMemberUID = assign.getStaffMember().getUid();
		this.issueUID = assign.getIssue().getUid();
		
		
	}
	
	public Long getIssueUID() {
		return issueUID;
	}


	public Long getStaffMemberUID() {
		return staffMemberUID;
	}


	public void setIssueUID(Long issueUID) {
		this.issueUID = issueUID;
	}


	public void setStaffMemberUID(Long staffMemberUID) {
		this.staffMemberUID = staffMemberUID;
	}

	
	
}
