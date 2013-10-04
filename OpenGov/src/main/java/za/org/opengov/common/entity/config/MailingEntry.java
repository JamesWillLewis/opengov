package za.org.opengov.common.entity.config;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OPENGOV_MAIL_ENTRY")
public class MailingEntry {

	private String address;
	private String name;
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


	@Column(name="NAME")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy="mailingEntry")
	public Set<MailingRole> getMailingRoles() {
		return mailingRoles;
	}
	
	public void setMailingRoles(Set<MailingRole> mailingRoles) {
		this.mailingRoles = mailingRoles;
	}
	
	
	
}
