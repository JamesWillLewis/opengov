package za.org.opengov.stockout.domain.dao;

import java.util.List;

import za.org.opengov.common.AbstractDao;
import za.org.opengov.stockout.domain.entity.TestEntity;

public interface TestDao extends AbstractDao<TestEntity, Long> {
	
    List<TestEntity> findAllTests();
    
}