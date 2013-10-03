package za.org.opengov.stockout.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.entity.Issue;
import za.org.opengov.common.service.IssueService;
import za.org.opengov.stockout.dao.StockoutReportDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.medical.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class StockoutServiceTest {

	@Autowired
	private StockoutService stockoutService;

	@Autowired
	private ProductService productService;

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private StockoutReportService reportService;

	@Autowired
	private IssueService issueService;

	@Autowired
	private StockoutReportDao stockoutReportDao;

	private Product p1;
	private Product p2;
	private Product p3;

	private Facility f1;
	private Facility f2;
	private Facility f3;

	@Before
	public void setUp() throws Exception {
		p1 = new Product();
		p2 = new Product();
		p3 = new Product();

		p1.setUid("prod1");
		p2.setUid("prod2");
		p3.setUid("prod3");

		productService.saveProduct(p1);
		productService.saveProduct(p2);
		productService.saveProduct(p3);

		f1 = new Facility();
		f2 = new Facility();
		f3 = new Facility();

		f1.setUid("fac1");
		f2.setUid("fac2");
		f3.setUid("fac3");

		facilityService.saveFacility(f1);
		facilityService.saveFacility(f2);
		facilityService.saveFacility(f3);

		Stockout stockout1 = new Stockout();
		Stockout stockout2 = new Stockout();
		Stockout stockout3 = new Stockout();
		Stockout stockout4 = new Stockout();

		stockout1.setFacility(f1);
		stockout1.setProduct(p1);

		stockout2.setFacility(f2);
		stockout2.setProduct(p1);

		stockout3.setFacility(f2);
		stockout3.setProduct(p3);

		stockout4.setFacility(f3);
		stockout4.setProduct(p2);

		stockoutService.saveStockout(stockout1);
		stockoutService.saveStockout(stockout2);
		stockoutService.saveStockout(stockout3);
		stockoutService.saveStockout(stockout4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddStockout() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetAllStockouts() {
		List<Stockout> stockouts = stockoutService.getAllStockouts();
		assertEquals(4, stockouts.size());
	}

	@Test
	public void testGetAllStockoutsForFacility() {
		List<Stockout> stockouts = stockoutService
				.getAllStockoutsForFacility("FAC2");
		assertEquals(2, stockouts.size());
	}

	@Test
	public void testGetAllStockoutsForProduct() {
		List<Stockout> stockouts = stockoutService
				.getAllStockoutsForProduct("PROD1");
		assertEquals(2, stockouts.size());
	}

	@Test
	public void testGetStockout() {
		Stockout stockout = stockoutService.getStockout("FAC2", "PrOD3");
		assertNotNull(stockout);
		assertEquals("FAC2", stockout.getFacility().getUid());
		assertEquals("PROD3", stockout.getProduct().getUid());
	}

	@Test
	public void testGetStockoutWithNull() {
		Stockout stockout = stockoutService.getStockout("FAC2", "PrOD5");
		System.out.println(stockout);
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

		productService.saveProduct(product1);
		productService.saveProduct(product2);
		productService.saveProduct(product3);

		Facility facility1 = new Facility();
		facility1.setUid("facility1");
		Facility facility2 = new Facility();
		facility2.setUid("facility2");

		facilityService.saveFacility(facility1);
		facilityService.saveFacility(facility2);

		Stockout stockout1 = new Stockout();
		Stockout stockout2 = new Stockout();
		Stockout stockout3 = new Stockout();
		Stockout stockout4 = new Stockout();

		stockout1.setProduct(product1);
		stockout1.setFacility(facility1);

		stockout2.setProduct(product2);
		stockout2.setFacility(facility1);

		stockout3.setProduct(product3);
		stockout3.setFacility(facility1);

		stockout4.setProduct(product3);
		stockout4.setFacility(facility2);

		stockoutService.saveStockout(stockout1);
		stockoutService.saveStockout(stockout2);
		stockoutService.saveStockout(stockout3);
		stockoutService.saveStockout(stockout4);

		StockoutReport report1 = new StockoutReport();
		StockoutReport report2 = new StockoutReport();
		StockoutReport report3 = new StockoutReport();
		StockoutReport report4 = new StockoutReport();
		StockoutReport report5 = new StockoutReport();
		StockoutReport report6 = new StockoutReport();
		StockoutReport report7 = new StockoutReport();
		StockoutReport report8 = new StockoutReport();

		report1.setStockout(stockout1);
		report2.setStockout(stockout2);
		report3.setStockout(stockout2);
		report4.setStockout(stockout3);
		report5.setStockout(stockout3);
		report6.setStockout(stockout2);
		report7.setStockout(stockout4);
		report8.setStockout(stockout4);

		reportService.submitStockoutReport(report1);
		reportService.submitStockoutReport(report2);
		reportService.submitStockoutReport(report3);
		reportService.submitStockoutReport(report4);
		reportService.submitStockoutReport(report5);
		reportService.submitStockoutReport(report6);
		reportService.submitStockoutReport(report7);
		reportService.submitStockoutReport(report8);

		// also check to ensure case is ignored
		Stockout mostCommon = stockoutService
				.getMostCommonlyReportedStockoutForFacility("facility1");

		assertEquals(stockout2.getUid(), mostCommon.getUid());

		Stockout mostCommon2 = stockoutService
				.getMostCommonlyReportedStockoutForFacility("sadsadadasddasd");
		Assert.assertNull(mostCommon2);

	}

	@Test
	@Rollback(true)
	public void testGetMostCommonStockoutsForFacility() {
		Product product1 = new Product();
		product1.setUid("PRODUCT 1");
		Product product2 = new Product();
		product2.setUid("PRODUCT 2");
		Product product3 = new Product();
		product3.setUid("PRODUCT 3");

		productService.saveProduct(product1);
		productService.saveProduct(product2);
		productService.saveProduct(product3);

		Facility facility1 = new Facility();
		facility1.setUid("facility1");
		Facility facility2 = new Facility();
		facility2.setUid("facility2");

		facilityService.saveFacility(facility1);
		facilityService.saveFacility(facility2);

		Stockout stockout1 = new Stockout();
		Stockout stockout2 = new Stockout();
		Stockout stockout3 = new Stockout();
		Stockout stockout4 = new Stockout();

		stockout1.setProduct(product1);
		stockout1.setFacility(facility1);

		stockout2.setProduct(product2);
		stockout2.setFacility(facility1);

		stockout3.setProduct(product3);
		stockout3.setFacility(facility1);

		stockout4.setProduct(product3);
		stockout4.setFacility(facility2);

		stockoutService.saveStockout(stockout1);
		stockoutService.saveStockout(stockout2);
		stockoutService.saveStockout(stockout3);
		stockoutService.saveStockout(stockout4);

		StockoutReport report1 = new StockoutReport();
		StockoutReport report2 = new StockoutReport();
		StockoutReport report3 = new StockoutReport();
		StockoutReport report4 = new StockoutReport();
		StockoutReport report5 = new StockoutReport();
		StockoutReport report6 = new StockoutReport();
		StockoutReport report7 = new StockoutReport();
		StockoutReport report8 = new StockoutReport();

		report1.setStockout(stockout1);
		report2.setStockout(stockout2);
		report3.setStockout(stockout2);
		report4.setStockout(stockout3);
		report5.setStockout(stockout3);
		report6.setStockout(stockout2);
		report7.setStockout(stockout4);
		report8.setStockout(stockout4);

		reportService.submitStockoutReport(report1);
		reportService.submitStockoutReport(report2);
		reportService.submitStockoutReport(report3);
		reportService.submitStockoutReport(report4);
		reportService.submitStockoutReport(report5);
		reportService.submitStockoutReport(report6);
		reportService.submitStockoutReport(report7);
		reportService.submitStockoutReport(report8);

		List<Stockout> mostCommons = stockoutService
				.getMostCommonlyReportedStockoutsForFacility("facility1", 3);

		assertEquals(stockout2.getUid(), mostCommons.get(0).getUid());
		assertEquals(stockout3.getUid(), mostCommons.get(1).getUid());
		assertEquals(stockout1.getUid(), mostCommons.get(2).getUid());

	}

	@Test
	public void updateAllStockoutPrioritiesTest() {
		StockoutReport report1 = new StockoutReport();
		StockoutReport report2 = new StockoutReport();
		StockoutReport report3 = new StockoutReport();
		StockoutReport report4 = new StockoutReport();
		StockoutReport report5 = new StockoutReport();

		Stockout stockout1 = new Stockout();
		Stockout stockout2 = new Stockout();

		Issue issue1 = new Issue();
		issue1.setSeverity(7);
		issue1.setStartTimestamp(DateTime.now().minusDays(7).toDate());

		issueService.put(issue1);

		Issue issue2 = new Issue();
		issue2.setSeverity(3);
		issue2.setStartTimestamp(DateTime.now().minusDays(3).toDate());

		issueService.put(issue2);

		stockout1.setIssue(issue1);
		stockout2.setIssue(issue2);

		
		stockout1.getStockoutReports().add(report1);
		stockout1.getStockoutReports().add(report2);
		stockout1.getStockoutReports().add(report3);
		stockout2.getStockoutReports().add(report4);
		stockout2.getStockoutReports().add(report5);
		
		stockoutService.saveStockout(stockout1);
		stockoutService.saveStockout(stockout2);
		
		stockoutService.updateAllStockoutPriorities();

		List<Issue> issues = issueService.getAll();

		for (Issue issue : issues) {
			System.out.println(issue.getPriority());
		}

	}

}
