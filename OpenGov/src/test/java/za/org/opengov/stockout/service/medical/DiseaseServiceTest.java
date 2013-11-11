package za.org.opengov.stockout.service.medical;

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

import za.org.opengov.stockout.entity.medical.Disease;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class DiseaseServiceTest {
	
	@Autowired
	private DiseaseService diseaseService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Rollback(true)
	public void testGetListOfDiseases() {
		Disease disease1 = new  Disease();
		Disease disease2 = new  Disease();
		Disease disease3 = new  Disease();
		
		disease1.setName("DISEASE 1");
		disease2.setName("DISEASE 2");
		disease3.setName("DISEASE 3");
		
		diseaseService.put(disease1);
		diseaseService.put(disease2);
		diseaseService.put(disease3);
		
		List<Disease> diseases = diseaseService.getAll();
		
		assertEquals(3, diseases.size());
	}

}
