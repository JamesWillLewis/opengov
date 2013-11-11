package za.org.opengov.stockout.web.admin.domain;

import javax.validation.constraints.NotNull;

public class AdminWrapper {

	@NotNull(message="May not be empty")
	private String name;
	
	@NotNull(message="May not be empty")
	private String password;
	
	public AdminWrapper(){
		
	}
	

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
