package za.org.opengov.stockout.service.medical;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class ProductBackfillTask {
	
	@Autowired
	private ProductService productService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Rollback(false)
	public void testPopulateDatabaseFromCSV() {
		File medicinesFile = new File(getClass().getClassLoader()
				.getResource("data/backfill/medicines.csv").getFile());

		// backfill clinics
		productService.populateDatabaseFromCSV(medicinesFile, "\t", "\"");
		
	}

}
