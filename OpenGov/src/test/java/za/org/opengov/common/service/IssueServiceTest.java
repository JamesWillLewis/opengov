package za.org.opengov.common.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class IssueServiceTest {
	
	@Autowired
	private IssueService issueService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculatePriority() {

		int sevMin = 0;
		int sevMax = 10;

		int occMin = 0;
		int occMax = 20;

		int durMin = 0;
		int durMax = 14;

		int severity = 8;
		int occurance = 4;
		int duration = 7;

		int priority1 = issueService.calculatePriority(severity, occurance,
				duration, sevMin, occMin, durMin, sevMax, occMax, durMax);

		severity = 4;
		occurance = 7;
		duration = 2;

		int priority2 = issueService.calculatePriority(severity, occurance,
				duration, sevMin, occMin, durMin, sevMax, occMax, durMax);

		severity = 9;
		occurance = 19;
		duration = 3;

		int priority3 = issueService.calculatePriority(severity, occurance,
				duration, sevMin, occMin, durMin, sevMax, occMax, durMax);

		Assert.assertTrue(priority1 > priority2 && priority3 > priority1);

	}

}
