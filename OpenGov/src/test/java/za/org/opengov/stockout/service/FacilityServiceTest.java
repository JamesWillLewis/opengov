package za.org.opengov.stockout.service;

import static org.junit.Assert.*;

import java.util.List;

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
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.medical.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class FacilityServiceTest {

	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockoutService stockoutService;

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
		facility.setDistrict("TEST REGION");
		facility.setUid("#TEST_CODE");

		//obviously assuming no facility was added with this code
		assertNull(facilityService.validateFacilityCode("#TEST_code"));

		facilityService.saveFacility(facility);

		Facility testFac = facilityService.validateFacilityCode("   #tEst_COde   ");
		assertNotNull(testFac);
		assertEquals(testFac.getLocalName(), "TEST LOCAL NAME");
		assertEquals(testFac.getDistrict(), "TEST REGION");

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
	
	@Test
	public void testFindAllFacilitiesWithStock(){
		
		Facility f1 = new Facility();
		Facility f2 = new Facility();
		Facility f3 = new Facility();
		
		f1.setUid("fac1");
		f2.setUid("fac2");
		f3.setUid("fac3");
		
		facilityService.saveFacility(f1);
		facilityService.saveFacility(f2);
		facilityService.saveFacility(f3);
		
		Product p1 = new Product();

		
		p1.setUid("med1");

		productService.saveProduct(p1);
		
		Stockout s1 = new Stockout();
		Stockout s2 = new Stockout();
		
		s1.setFacility(f3);
		s1.setProduct(p1);
		s1.setResolved(false);
		
		s2.setFacility(f1);
		s2.setProduct(p1);
		s2.setResolved(true);
		
		stockoutService.saveStockout(s1);
		stockoutService.saveStockout(s2);
		
		List<Facility> facilities = facilityService.getAllFacilitiesWithStock(p1);
		
		assertEquals(2, facilities.size());
		
		for(Facility f: facilities){
			assertTrue(!f.getUid().equals(f3.getUid()));
		}
		
		
	}

}
