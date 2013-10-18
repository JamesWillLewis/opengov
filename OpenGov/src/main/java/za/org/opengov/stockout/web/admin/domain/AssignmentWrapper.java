package za.org.opengov.stockout.web.admin.domain;

public class AssignmentWrapper {
	
	private Long issueUID;
	
	private Long staffMemberUID;
	

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


	public AssignmentWrapper() {
	}

	
	
}
