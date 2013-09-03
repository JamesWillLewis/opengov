package za.org.opengov.stockout.test.dao;

import java.util.List;

import za.org.opengov.common.AbstractDao;
import za.org.opengov.stockout.test.model.TestEntity;

public interface TestDao extends AbstractDao<TestEntity, Long> {
	
    List<TestEntity> findAllTests();
    
}