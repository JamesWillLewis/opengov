package za.org.opengov.common.service.config.impl;

import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.entity.config.MailingEntry;
import za.org.opengov.common.entity.config.MailingRole;
import za.org.opengov.common.service.config.MailingEntryService;
import za.org.opengov.common.service.config.MailingRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class MailingEntryServiceImplTest {
	
	@Autowired
	private MailingEntryService mailingEntryService;
	
	@Autowired
	private MailingRoleService mailingRoleService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllMailingEntriesForRole() {
		
		MailingRole mailingRole1 = new MailingRole();
		MailingRole mailingRole2 = new MailingRole();
		MailingRole mailingRole3 = new MailingRole();
		
		MailingEntry mailingEntry1 = new MailingEntry();
		MailingEntry mailingEntry2 = new MailingEntry();

		mailingEntry1.setAddress("james.will.lewis@gmail.com");
		mailingEntry2.setAddress("testytest@test.tes");
		
		mailingEntryService.put(mailingEntry1);
		mailingEntryService.put(mailingEntry2);
		
		mailingRole1.setRoleID("role1");
		mailingRole1.setMailingEntry(mailingEntry1);
		mailingEntry1.getMailingRoles().add(mailingRole1);
		
		mailingRole2.setRoleID("role1");
		mailingRole2.setMailingEntry(mailingEntry2);
		mailingEntry2.getMailingRoles().add(mailingRole2);
		
		mailingRole3.setRoleID("role2");
		mailingRole3.setMailingEntry(mailingEntry2);
		mailingEntry2.getMailingRoles().add(mailingRole3);
		
		mailingRoleService.put(mailingRole1);
		mailingRoleService.put(mailingRole2);
		mailingRoleService.put(mailingRole3);
		
		List<MailingEntry> entries1 = mailingEntryService.getAllMailingEntriesForRole("role1");
		List<MailingEntry> entries2 = mailingEntryService.getAllMailingEntriesForRole("role2");
		
		Assert.assertEquals(2, entries1.size());
		Assert.assertEquals(1, entries2.size());
		
		Set<MailingRole> roles1 = mailingEntryService.get("testytest@test.tes").getMailingRoles();
		Set<MailingRole> roles2 = mailingEntryService.get("james.will.lewis@gmail.com").getMailingRoles();
		
		Assert.assertEquals(2, roles1.size());
		Assert.assertEquals(1, roles2.size());
	}

}
