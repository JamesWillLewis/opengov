package za.org.opengov.common.entity.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OPENGOV_MAIL_ROLE")
public class MailingRole {
	
	private Long uid;
	private MailingEntry mailingEntry;
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
	
	@Column(name="ROLE")
	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	
	
}
