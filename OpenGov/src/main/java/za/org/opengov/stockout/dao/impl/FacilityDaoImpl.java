package za.org.opengov.stockout.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.entity.Facility;

@Repository("facilityDao")
public class FacilityDaoImpl extends AbstractDaoImpl<Facility, String> implements FacilityDao {

	protected FacilityDaoImpl() {
		super(Facility.class);
	}

}
