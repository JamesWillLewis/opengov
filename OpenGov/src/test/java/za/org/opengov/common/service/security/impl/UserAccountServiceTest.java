package za.org.opengov.common.service.security.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.entity.security.User;
import za.org.opengov.common.service.security.UserAccountService;
import za.org.opengov.common.service.security.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
public class UserAccountServiceTest {
	
	@Autowired
	private UserAccountService accountService;
	
	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddUser() {
		String username = "TEST";
		String password = "PASSWORD";
		String[] roles = {"ADMIN","USER"};
		
		accountService.addUser(username, password, roles);
		
		User user = userService.get(username);
		assertEquals("ADMIN,USER", user.getRoles());
		assertNotSame("PASSWORD", user.getPassword());
	}
	
	@Test
	@Rollback(false)
	public void addTestingUser() {
		String username = "opengov";
		String password = "jamesven";
		String[] roles = {"ADMIN","DEV","STAFF"};
		
		accountService.addUser(username, password, roles);

	}

}
