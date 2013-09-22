package za.org.opengov.stockout.service.medical;

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

import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutReportService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class ProductServiceTest {

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private StockoutReportService reportService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDao productDao;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Rollback(true)
	public void testGetMostCommonStockoutForFacility() {
		Product product1 = new Product();
		product1.setUid("PRODUCT 1");
		Product product2 = new Product();
		product2.setUid("PRODUCT 2");
		Product product3 = new Product();
		product3.setUid("PRODUCT 3");

		productDao.saveOrUpdate(product1);
		productDao.saveOrUpdate(product2);
		productDao.saveOrUpdate(product3);
		
		Facility facility1 = new Facility();
		facility1.setUid("FAC1");
		Facility facility2 = new Facility();
		facility2.setUid("FAC2");

		facilityService.saveFacility(facility1);
		facilityService.saveFacility(facility2);

		StockoutReport report1 = new StockoutReport();
		StockoutReport report2 = new StockoutReport();
		StockoutReport report3 = new StockoutReport();
		StockoutReport report4 = new StockoutReport();
		StockoutReport report5 = new StockoutReport();
		StockoutReport report6 = new StockoutReport();
		StockoutReport report7 = new StockoutReport();
		StockoutReport report8 = new StockoutReport();

		report1.setFacility(facility1);
		report2.setFacility(facility1);
		report3.setFacility(facility1);
		report4.setFacility(facility1);
		report5.setFacility(facility1);
		report6.setFacility(facility1);
		report7.setFacility(facility2);
		report8.setFacility(facility2);

		report1.setProduct(product1);
		report2.setProduct(product2);
		report3.setProduct(product2);
		report4.setProduct(product3);
		report5.setProduct(product3);
		report6.setProduct(product2);
		report7.setProduct(product3);
		report8.setProduct(product3);

		reportService.submitStockoutReport(report1);
		reportService.submitStockoutReport(report2);
		reportService.submitStockoutReport(report3);
		reportService.submitStockoutReport(report4);
		reportService.submitStockoutReport(report5);
		reportService.submitStockoutReport(report6);
		reportService.submitStockoutReport(report7);
		reportService.submitStockoutReport(report8);

		// also check to ensure case is ignored
		Product mostCommon = productService
				.getMostCommonStockoutForFacility("fAc1");

		assertEquals(product2.getUid(), mostCommon.getUid());

	}

	@Test
	@Rollback(true)
	public void testGetClosestMatch() {
		
		Product product1 = new Product();
		product1.setUid("PRODUCT 1");
		product1.setName("Panaparaparado");
		Product product2 = new Product();
		product2.setUid("PRODUCT 2");
		product2.setName("Panado");
		Product product3 = new Product();
		product3.setUid("PRODUCT 3");
		product3.setName("Apsirin");
		Product product4 = new Product();
		product4.setUid("PRODUCT 4");
		product4.setName("Viritol");
		Product product5 = new Product();
		product5.setUid("PRODUCT 5");
		product5.setName("Discrip");
		Product product6 = new Product();
		product6.setUid("PRODUCT 6");
		product6.setName("Paracetomol");

		productDao.saveOrUpdate(product1);
		productDao.saveOrUpdate(product2);
		productDao.saveOrUpdate(product3);
		productDao.saveOrUpdate(product4);
		productDao.saveOrUpdate(product5);
		productDao.saveOrUpdate(product6);
		
		Product match = productService.getClosestMatch("PAAAANNADooO");
		
		assertEquals(product2.getName(), match.getName());
		
	}

}
