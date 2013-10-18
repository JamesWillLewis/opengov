package za.org.opengov.common.service.security;

public interface UserAccountService {
	
	public void addUser(String username, String password, String[] roles);

}
