package za.org.opengov.stockout.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.service.FacilityService;

@Service("facilityService")
@Transactional
public class FacilityServiceImpl implements FacilityService {
	
	@Autowired
	private FacilityDao facilityDao;

	@Override
	public Facility validateFacilityCode(String facilityCode) {
		Facility facility = facilityDao.findById(facilityCode);	
		return facility;
	}

	@Override
	public void saveFacility(Facility facility) {
		facilityDao.saveOrUpdate(facility);
	}

}
