package za.org.opengov.stockout.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.org.opengov.stockout.entities.TestEntity;
import za.org.opengov.stockout.repositories.TestRepository;

@Service("testService")
public class TestService {

	@Autowired
	private TestRepository testRepository;
	
	public List<TestEntity> getAllTests(){
		return testRepository.findAll();
	}
	
}
