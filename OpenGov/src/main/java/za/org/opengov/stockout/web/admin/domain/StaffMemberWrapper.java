package za.org.opengov.stockout.web.admin.domain;

import org.hibernate.validator.constraints.NotEmpty;

import za.org.opengov.common.entity.StaffMember;


public class StaffMemberWrapper {
	
	@NotEmpty
	private String staffCode;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String surname;
	
	private long uid;
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}


	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	

}
