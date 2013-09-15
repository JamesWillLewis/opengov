package za.org.opengov.stockout.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.service.FacilityService;

@Service("facilityService")
@Transactional
public class FacilityServiceImpl implements FacilityService {

	@Override
	public Facility validateFacilityCode(String facilityCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
