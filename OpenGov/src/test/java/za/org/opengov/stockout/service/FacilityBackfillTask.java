package za.org.opengov.stockout.service;

import java.io.File;

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

import za.org.opengov.stockout.entity.FacilityType;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class FacilityBackfillTask {
	
	@Autowired
	private FacilityService facilityService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Rollback(false)
	public void testPopulateDatabaseFromCSV() {
		
		File clinicsFile = new File(getClass().getClassLoader().getResource("data/backfill/clinics.csv").getFile());
		
		//backfill clinics
		facilityService.populateDatabaseFromCSV(clinicsFile, ",", "\"", FacilityType.CLINIC);
	}

}
