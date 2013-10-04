package za.org.opengov.stockout.schedule;

import java.util.List;

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

import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutReportService;
import za.org.opengov.stockout.service.StockoutService;
import za.org.opengov.stockout.service.medical.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class NotificationServiceTest {
	
	@Autowired 
	private StockoutService stockoutService;
	
	@Autowired
	private StockoutReportService stockoutReportService;
	
	@Autowired
	private StockoutNotificationService notificationService;

	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private ProductService productService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendStockoutNotifications() {
		Facility f = new Facility();
		f.setUid("TEST PROD");
		Product p = new Product();
		p.setUid("TEST FAC");
		
		productService.saveProduct(p);
		facilityService.saveFacility(f);
		
		stockoutReportService.submitStockoutReport(p.getUid(), f.getUid(), null, "test1", false);
		stockoutReportService.submitStockoutReport(p.getUid(), f.getUid(), null, "test2", false);
		stockoutReportService.submitStockoutReport(p.getUid(), f.getUid(), null, "test3", false);
		
		List<Stockout> stockouts = stockoutService.getAllStockouts();

		Assert.assertEquals(1, stockouts.size());
		Assert.assertEquals(3, stockouts.get(0).getStockoutReports().size());
		
		notificationService.sendNotifications();
		
		//must manually check email to ensure email was sent
	}

}
