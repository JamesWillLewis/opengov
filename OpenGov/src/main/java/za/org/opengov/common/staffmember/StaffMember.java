package za.org.opengov.common.staffmember;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OPENGOV_STAFF_MEMBER")
public class StaffMember {
	
	private String staffCode;
	private String name;
	private String surname;
	
	public StaffMember() {
	}

	@Id
	@Column(name="STAFF_CODE", unique=true, nullable=false)
	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="SURNAME")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	

}
