package za.org.opengov.stockout.domain.test;

import java.util.List;

import za.org.opengov.common.AbstractDao;

public interface TestDao extends AbstractDao<TestEntity, Long> {
	
    List<TestEntity> findAllTests();
    
}