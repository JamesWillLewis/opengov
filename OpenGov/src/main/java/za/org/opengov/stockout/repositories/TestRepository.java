package za.org.opengov.stockout.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import za.org.opengov.stockout.entities.TestEntity;

@Repository("testRepo")
public class TestRepository extends HibernateRepository {

	public List<TestEntity> findAll() {
		return getHibernateTemplate().find("from TestEntity");
	}
	

}
