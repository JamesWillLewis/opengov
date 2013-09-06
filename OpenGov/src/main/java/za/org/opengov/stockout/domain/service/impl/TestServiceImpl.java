package za.org.opengov.stockout.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.domain.dao.impl.TestDaoImpl;
import za.org.opengov.stockout.domain.entity.TestEntity;
import za.org.opengov.stockout.domain.service.TestService;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDaoImpl testDao;
	
	@Transactional
	public List<TestEntity> getAllTests(){
		return testDao.findAllTests();
	}
	
}