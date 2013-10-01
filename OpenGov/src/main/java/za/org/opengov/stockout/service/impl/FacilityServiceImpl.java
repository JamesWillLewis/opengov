package za.org.opengov.stockout.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.util.StringMatchable;
import za.org.opengov.common.util.StringMatcher;
import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;

@Service("facilityService")
@Transactional
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private FacilityDao facilityDao;

	@Override
	public Facility validateFacilityCode(String facilityCode) {
		facilityCode = facilityCode.toUpperCase().trim();
		Facility facility = facilityDao.findById(facilityCode);
		return facility;
	}

	@Override
	public void saveFacility(Facility facility) {
		facility.setUid(facility.getUid().toUpperCase());
		facilityDao.saveOrUpdate(facility);
	}

	@Override
	public Facility getClosestMatch(String facilityIdentifier) {

		List<Facility> facilities = facilityDao.findAll();

		StringMatcher matcher = new StringMatcher();

		for (Facility f : facilities) {

			FacilityCodeWrapper codeWrapper = new FacilityCodeWrapper(f);
			FacilityNameWrapper nameWrapper = new FacilityNameWrapper(f);

			matcher.addStringMatchable(codeWrapper);
			matcher.addStringMatchable(nameWrapper);
		}
		StringMatchable matchable = matcher.getClosestMatch(facilityIdentifier);

		if (matchable instanceof FacilityCodeWrapper) {
			return ((FacilityCodeWrapper) matchable).getFacility();
		} else if (matchable instanceof FacilityNameWrapper) {
			return ((FacilityNameWrapper) matchable).getFacility();
		} else {
			return null;
		}
	}

	@Override
	public Facility getNearestFacilityWithStock(Product product,
			Facility originFacility) {
		List<Facility> facilitiesWithStock = getAllFacilitiesWithStock(product);

		double originLng = originFacility.getLongitudeDecimalDegrees();
		double originLat = originFacility.getLatitudeDecimalDegress();

		Facility closestFacility = null;
		double bestDistance = Double.MAX_VALUE;

		// search for the nearest facility using Euclidean distance (as the crow
		// flies)
		// using the facility's coordinates
		for (Facility f : facilitiesWithStock) {
			double destLng = f.getLongitudeDecimalDegrees();
			double destLat = f.getLatitudeDecimalDegress();

			double distance = Math.sqrt((destLng - originLng)
					* (destLng - originLng) + (destLat - originLat)
					* (destLat - originLat));
			if (distance < bestDistance) {
				bestDistance = distance;
				closestFacility = f;
			}
		}

		return closestFacility;
	}

	@Override
	public List<Facility> getAllFacilitiesWithStock(Product product) {
		return facilityDao.findAllWithoutStockoutOfProduct(product.getUid());
	}

	private class FacilityCodeWrapper implements StringMatchable {

		private Facility facility;

		public FacilityCodeWrapper(Facility facility) {
			this.facility = facility;
		}

		public Facility getFacility() {
			return facility;
		}

		@Override
		public String getStringToMatch() {
			return facility.getUid();
		}

	}

	private class FacilityNameWrapper implements StringMatchable {

		private Facility facility;

		public FacilityNameWrapper(Facility facility) {
			this.facility = facility;
		}

		public Facility getFacility() {
			return facility;
		}

		@Override
		public String getStringToMatch() {
			return facility.getLocalName();
		}

	}

}
