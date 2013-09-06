package za.org.opengov.stockout.domain.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.FacilityDao;
import za.org.opengov.stockout.domain.entity.Facility;

@Repository("facilityDao")
public class FacilityDaoImpl extends AbstractDaoImpl<Facility, Long> implements FacilityDao {

	protected FacilityDaoImpl() {
		super(Facility.class);
	}

}
