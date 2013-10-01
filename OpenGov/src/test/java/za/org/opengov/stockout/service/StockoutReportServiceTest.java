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
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Product;

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
		// System.out.println("COUNT " + productDao.findAll().size());
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
