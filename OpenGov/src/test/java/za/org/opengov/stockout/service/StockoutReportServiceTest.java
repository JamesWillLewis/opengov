package za.org.opengov.stockout.service;

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

import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.Facility;
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
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	@Test
	@Rollback(true)
	public void testSubmitStockoutReport() {
		System.out.println("COUNT " + productDao.findAll().size());
	}


}
