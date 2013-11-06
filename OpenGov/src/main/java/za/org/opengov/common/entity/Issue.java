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
package za.org.opengov.common.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Domain entity for Issue concept.
 * Annotated to allow object-relational mapping using JPA/Hibernate.
 * 
 * An Issue is a fundamental concept of OpenGov, which represents any possible issue within a community.
 * A sub-system within OpenGov will typically have another entity referencing an Issue,
 * for example, the StockOut system has a StockOut entity which has a 1:1 association to an Issue.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "OPENGOV_ISSUE")
public class Issue {

	/**
	 * Primary key
	 */
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
