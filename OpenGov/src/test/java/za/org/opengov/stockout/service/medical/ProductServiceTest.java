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
