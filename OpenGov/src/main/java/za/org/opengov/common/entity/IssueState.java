package za.org.opengov.common.entity;

public enum IssueState {
	OPEN("Pending"), IN_PROGRESS("In Progress"), RESOLVED("Resolved"), CLOSED("Closed");
	
	private String humanReadable;
	
	private IssueState(String humanReadable) {
		this.humanReadable = humanReadable;
	}
	
	public String toString() {
		return humanReadable;
	}
	
}
