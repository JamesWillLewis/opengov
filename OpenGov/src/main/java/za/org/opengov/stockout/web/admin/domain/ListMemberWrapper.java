package za.org.opengov.stockout.web.admin.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ListMemberWrapper {
	
	@NotEmpty(message="please enter a name")
	private String name;
	
	@Email(message="please enter a valid email address")
	private String email;
	
	public ListMemberWrapper(){
		
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
