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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Domain entity for Mailing Entry concept. Annotated to allow object-relational
 * mapping using JPA/Hibernate.
 * 
 * A mailing entry represents an email address and the name of the person to
 * whom the address is associated. A mailing entry is used as the destination of
 * notifications, and aggregates various mailing roles (such as stockout
 * notifications).
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "OPENGOV_MAIL_ENTRY")
public class MailingEntry {

	/**
	 * Email address. Must be in address@domain format.
	 */
	private String address;
	/**
	 * Name of the person who holds the email address.
	 */
	private String name;
	/**
	 * Aggregated roles for this address.
	 */
	private Set<MailingRole> mailingRoles = new HashSet<MailingRole>();

	public MailingEntry() {
	}

	@Id
	@Column(name = "ADDRESS", unique = true, nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "mailingEntry")
	public Set<MailingRole> getMailingRoles() {
		return mailingRoles;
	}

	public void setMailingRoles(Set<MailingRole> mailingRoles) {
		this.mailingRoles = mailingRoles;
	}

}
