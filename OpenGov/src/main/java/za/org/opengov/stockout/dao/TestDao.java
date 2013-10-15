package za.org.opengov.stockout.dao;

import java.util.List;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.stockout.entity.TestEntity;

@Deprecated
public interface TestDao extends AbstractDao<TestEntity, Long> {
	
    List<TestEntity> findAllTests();
    
}