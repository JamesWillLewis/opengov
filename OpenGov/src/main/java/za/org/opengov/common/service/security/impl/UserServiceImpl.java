package za.org.opengov.common.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.security.UserDao;
import za.org.opengov.common.entity.security.User;
import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.common.service.security.UserService;

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<UserDao, User, String>
		implements UserService {

	@Autowired
	public UserServiceImpl(UserDao dao) {
		super(dao);
	}

	@Override
	public void saveEncrypted(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encryptedPass = encoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		put(user);
	}

}
