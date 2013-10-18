package za.org.opengov.common.dao.security.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.common.dao.security.UserDao;
import za.org.opengov.common.entity.security.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements
		UserDao {

	protected UserDaoImpl() {
		super(User.class);
	}

}
