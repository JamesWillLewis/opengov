package za.org.opengov.stockout.service;

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

import za.org.opengov.stockout.entity.Facility;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class FacilityServiceTest {

	@Autowired
	private FacilityService facilityService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Rollback(true)
	public void testValidateFacilityCode() {

		Facility facility = new Facility();
		facility.setLocalName("TEST LOCAL NAME");
		facility.setRegion("TEST REGION");
		facility.setUid("#TEST_CODE");

		//obviously assuming no facility was added with this code
		assertNull(facilityService.validateFacilityCode("#TEST_CODE"));

		facilityService.saveFacility(facility);

		Facility testFac = facilityService.validateFacilityCode("#TEST_CODE");
		assertNotNull(testFac);
		assertEquals(testFac.getLocalName(), "TEST LOCAL NAME");
		assertEquals(testFac.getRegion(), "TEST REGION");

	}

}
