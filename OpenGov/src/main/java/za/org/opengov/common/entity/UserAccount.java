package za.org.opengov.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OPENGOV_ACCOUNT")
public class UserAccount {
	
	private String username;
	private String password;
	
	public UserAccount() {
		// TODO Auto-generated constructor stub
	}
	
	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Id
	@Column(name="USERNAME")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	

}
