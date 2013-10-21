package za.org.opengov.common.service.security;

import java.util.List;

import za.org.opengov.common.entity.security.User;
import za.org.opengov.common.service.AbstractService;

public interface UserService extends AbstractService<User, String>{

	public void saveEncrypted(User user);
	
	public List<User> getUsersWithRole(String role);
	
}
