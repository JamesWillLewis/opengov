package za.org.opengov.stockout.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.test.dao.TestDaoImpl;
import za.org.opengov.stockout.test.model.TestEntity;


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
