package za.org.opengov.stockout.domain.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("testService")
@Transactional
public class TestService {

	@Autowired
	private TestDaoImpl testDao;
	
	@Transactional
	public List<TestEntity> getAllTests(){
		return testDao.findAllTests();
	}
	
}
