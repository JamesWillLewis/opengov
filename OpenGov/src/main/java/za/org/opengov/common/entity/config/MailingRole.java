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
package za.org.opengov.common.entity.config;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Domain entity for Mailing Role concept. Annotated to allow object-relational
 * mapping using JPA/Hibernate.
 * 
 * A mailing role is a mapping of a particular mailing-entry to a role, where a
 * role could be 'stockout' in the case of the stock-out notification mailing
 * service.
 * 
 * A mailing role allows a whole mailing list of mailing entries to be aggregated.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "OPENGOV_MAIL_ROLE")
public class MailingRole {

	/**
	 * Primary key.
	 */
	private Long uid;
	/**
	 * Mailing entry who is assigned to the role.
	 */
	private MailingEntry mailingEntry;
	/**
	 * The role ID tag (i.e. stockout).
	 */
	private String roleID;

	public MailingRole() {
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

	@ManyToOne
	@JoinColumn(name = "FK_MAIL_ENTRY")
	public MailingEntry getMailingEntry() {
		return mailingEntry;
	}

	public void setMailingEntry(MailingEntry mailingEntry) {
		this.mailingEntry = mailingEntry;
	}

	@Column(name = "ROLE")
	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

}
