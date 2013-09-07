package za.org.opengov.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPENGOV_ISSUE")
public class Issue {

	private Long uid;
	private Date startTimestamp;
	private Date endTimestamp;
	private IssueState state;
	private int severity;
	private int priority;
	private String details;
	private boolean requireFollowUp;

	public Issue() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID", unique = true, nullable = false)
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Column(name = "START_DATETIME")
	public Date getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	@Column(name = "END_DATETIME")
	public Date getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	@Column(name = "STATE")
	@Enumerated(EnumType.STRING)
	public IssueState getState() {
		return state;
	}

	public void setState(IssueState state) {
		this.state = state;
	}

	@Column(name = "SEVERITY")
	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	@Column(name = "PRIORITY")
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Column(name = "DETAILS", length = 512)
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name = "REQUIRE_FOLLOWUP")
	public boolean getRequireFollowUp() {
		return requireFollowUp;
	}

	public void setRequireFollowUp(boolean requireFollowUp) {
		this.requireFollowUp = requireFollowUp;
	}

}
