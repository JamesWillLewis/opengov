package za.org.opengov.common.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.entity.security.User;
import za.org.opengov.common.service.security.UserAccountService;
import za.org.opengov.common.service.security.UserService;

@Service("userAccountService")
public class UserAccountServiceImpl implements UserDetailsService, UserAccountService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		System.out.println("FINDING USER " + username);
		User authUser = findUser(username);

		if(authUser==null){
			throw new UsernameNotFoundException("User does not exist");
		}
		String password = authUser.getPassword();

		System.out.println(password);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails details = new org.springframework.security.core.userdetails.User(
				username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked,
				getGrantedAuthorities(authUser));

		return details;
	}

	private User findUser(String userName) {
		System.out.println(userService);
		return userService.get(userName);
	}

	public void addUser(String username, String password, String[] roles) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		String commaSepRoles = "";
		for(int i = 0; i < roles.length; i++){
			commaSepRoles += roles[i];
			if(i < roles.length-1){
				commaSepRoles += ',';
			}
		}
		user.setRoles(commaSepRoles);
		userService.saveEncrypted(user);
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(user
				.getRoles());
	}
}
