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
		assertNull(facilityService.validateFacilityCode("#TEST_code"));

		facilityService.saveFacility(facility);

		Facility testFac = facilityService.validateFacilityCode("   #tEst_COde   ");
		assertNotNull(testFac);
		assertEquals(testFac.getLocalName(), "TEST LOCAL NAME");
		assertEquals(testFac.getRegion(), "TEST REGION");

	}
	
	@Test
	public void testGetClosestMatch(){
		Facility facility1 = new Facility();
		facility1.setLocalName("Hottentots Holland");
		facility1.setUid("HHH");
		
		Facility facility2 = new Facility();
		facility2.setLocalName("Tigerburg Hospital");
		facility2.setUid("TB");
		
		Facility facility3 = new Facility();
		facility3.setLocalName("Kayalitcha Clinic");
		facility3.setUid("KYL");
		
		Facility facility4 = new Facility();
		facility4.setLocalName("Groot Schuur");
		facility4.setUid("GS");
		
		Facility facility5 = new Facility();
		facility5.setLocalName("Mediclinic");
		facility5.setUid("MC");

		facilityService.saveFacility(facility1);
		facilityService.saveFacility(facility2);
		facilityService.saveFacility(facility3);
		facilityService.saveFacility(facility4);
		facilityService.saveFacility(facility5);
		

		assertEquals(facility2.getLocalName(), facilityService.getClosestMatch("tb").getLocalName());

		assertEquals(facility5.getLocalName(), facilityService.getClosestMatch("mediklinik").getLocalName());
		
		assertEquals(facility1.getLocalName(), facilityService.getClosestMatch("Hottentots").getLocalName());
		
		assertEquals(facility1.getLocalName(), facilityService.getClosestMatch("HH").getLocalName());
		
	}

}