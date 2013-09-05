package za.org.opengov.stockout.domain.dao.impl;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.FacilityDao;
import za.org.opengov.stockout.domain.entity.Facility;

public class FacilityDaoImpl extends AbstractDaoImpl<Facility, Long> implements FacilityDao {

	protected FacilityDaoImpl(Class<Facility> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
