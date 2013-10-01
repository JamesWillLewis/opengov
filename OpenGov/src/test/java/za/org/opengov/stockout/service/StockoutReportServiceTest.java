package za.org.opengov.stockout.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

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

import za.org.opengov.common.dao.IssueDao;
import za.org.opengov.common.entity.Issue;
import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.medical.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class StockoutReportServiceTest {

	@Autowired
	private StockoutReportService reportService;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private FacilityDao facilityDao;

	@Autowired
	private IssueDao issueDao;
	
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockoutService stockoutService;

	private final String PRODUCT_CODE = "P123";
	private final String FACILITY_CODE = "F123";

	/**
	 * Insert sample Product & Facility data.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Product product = new Product();
		product.setUid(PRODUCT_CODE);

		Facility facility = new Facility();
		facility.setUid(FACILITY_CODE);

		productDao.saveOrUpdate(product);
		facilityDao.saveOrUpdate(facility);
	}

	/**
	 * Delete sample Product & Facility data.
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	@Test
	@Rollback(true)
	public void testSubmitStockoutReport() {
		
		Facility facility1 = new Facility();
		facility1.setUid("CT_KAYA");
		facility1.setLocalName("Kayalitsha clinic");
		
		Facility facility2 = new Facility();
		facility2.setUid("CT_CF");
		facility2.setLocalName("Cape Flats clinic");
	
		facilityService.saveFacility(facility1);
		facilityService.saveFacility(facility2);
		
		Product product1 = new Product();
		product1.setUid("PRC_1");
		product1.setName("Panado");
		
		Product product2 = new Product();
		product2.setUid("PRC_2");
		product2.setName("Painamol");
		
		
		productService.saveProduct(product1);
		productService.saveProduct(product2);
		
		Product p = productService.getClosestMatch("panaada");
		Facility f = facilityService.getClosestMatch("kayalitcha");
		
		reportService.submitStockoutReport(p.getUid(), f.getUid(), null, null, null, false, false);
		reportService.submitStockoutReport(p.getUid(), f.getUid(), null, null, null, false, false);
		reportService.submitStockoutReport(p.getUid(), f.getUid(), null, null, null, false, false);
		reportService.submitStockoutReport(p.getUid(), f.getUid(), null, null, null, false, false);
		
		
		StockoutReport report = reportService.getRecentlyReportedStockouts(1).get(0);
		Stockout stockout = report.getStockout();
		Assert.assertEquals("PRC_1", stockout.getProduct().getUid());
		Assert.assertEquals("CT_KAYA", stockout.getFacility().getUid());
		
		List<Stockout> stockouts = stockoutService.getAllStockoutsForFacility(f.getUid());
		Assert.assertEquals(1, stockouts.size());
	}

	@Test
	@Rollback(true)
	public void testGetRecentlyReportedStockouts() {

		// create stockout reports
		StockoutReport report1 = new StockoutReport();
		StockoutReport report2 = new StockoutReport();
		StockoutReport report3 = new StockoutReport();
		StockoutReport report4 = new StockoutReport();
		StockoutReport report5 = new StockoutReport();

		// set different submission times for issues
		Calendar date = Calendar.getInstance();
		date.set(2009, 5, 5);
		report1.setTimestamp(date.getTime());
		date.set(2008, 5, 5);
		report2.setTimestamp(date.getTime());
		date.set(2010, 5, 5);
		report3.setTimestamp(date.getTime());
		date.set(2005, 5, 5);
		report4.setTimestamp(date.getTime());
		date.set(2013, 5, 5);
		report5.setTimestamp(date.getTime());

		// submit stockout reports
		reportService.submitStockoutReport(report1);
		reportService.submitStockoutReport(report2);
		reportService.submitStockoutReport(report3);
		reportService.submitStockoutReport(report4);
		reportService.submitStockoutReport(report5);

		int limit = 4;

		// ensure stockout reports retrieved in correct order
		List<StockoutReport> reports = reportService
				.getRecentlyReportedStockouts(limit);

		Assert.assertEquals(limit, reports.size());

		Assert.assertEquals(report5.getTimestamp(), reports.get(0)
				.getTimestamp());
		Assert.assertEquals(report3.getTimestamp(), reports.get(1)
				.getTimestamp());
		Assert.assertEquals(report1.getTimestamp(), reports.get(2)
				.getTimestamp());
		Assert.assertEquals(report2.getTimestamp(), reports.get(3)
				.getTimestamp());

	}
	



}
