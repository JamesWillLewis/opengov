package za.org.opengov.common.entity;

public enum IssueState {
	OPEN("Pending..."), ASSIGNED("Assigned..."), IN_PROGRESS("In Progress..."), RESOLVED(
			"Resolved."), CLOSED("Closed.");

	private String readableText;

	private IssueState(String readableText) {
		this.readableText = readableText;
	}

	@Override
	public String toString() {
		return readableText;
	}
}
