package za.org.opengov.stockout.domain.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.criteria.CriteriaBuilderImpl;
import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.TestDao;
import za.org.opengov.stockout.domain.entity.TestEntity;

@Repository("testRepo")
public class TestDaoImpl extends AbstractDaoImpl<TestEntity, Long> implements TestDao {

	public TestDaoImpl() {
		super(TestEntity.class);
	}

	@Override
	public List<TestEntity> findAllTests() {
		return findAll();
	}


}
