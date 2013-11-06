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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <i>Junction-table</i> to resolve many-to-many relationship between staff and issue,
 * allowing multiple staff members to be assigned to a single issue, and multiple
 * issues to be assigned to a single staff member. 
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "OPENGOV_ASSIGNMENT")
public class Assignment {

	/**
	 * Primary key
	 */
	private Long uid;
	/**
	 * The issue which for which the staff member is assigned to.
	 */
	private Issue issue;
	/**
	 * The staff member which is assigned to the issue.
	 */
	private StaffMember staffMember;

	public Assignment() {
	}

	/**
	 * 
	 * @return The UID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID", unique = true, nullable = false)
	public Long getUid() {
		return uid;
	}

	/**
	 * 
	 * @param uid
	 *            The UID to set.
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * 
	 * @return The issue.
	 */
	@ManyToOne
	@JoinColumn(name = "FK_ISSUE")
	public Issue getIssue() {
		return issue;
	}

	/**
	 * 
	 * @param issue
	 *            The issue to set.
	 */
	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	/**
	 * 
	 * @return The staffMember.
	 */
	@ManyToOne
	@JoinColumn(name = "FK_STAFF_MEMBER")
	public StaffMember getStaffMember() {
		return staffMember;
	}

	/**
	 * 
	 * @param staffMember
	 *            The staffMember to set.
	 */
	public void setStaffMember(StaffMember staffMember) {
		this.staffMember = staffMember;
	}

}
