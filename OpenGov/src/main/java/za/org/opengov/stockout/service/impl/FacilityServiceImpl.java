package za.org.opengov.stockout.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.common.util.CSVParser;
import za.org.opengov.common.util.StringMatchable;
import za.org.opengov.common.util.StringMatcher;
import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.FacilityType;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;

@Service("facilityService")
@Transactional
public class FacilityServiceImpl extends
		AbstractServiceImpl<FacilityDao, Facility, String> implements
		FacilityService {

	@Autowired
	public FacilityServiceImpl(FacilityDao dao) {
		super(dao);
	}

	@Override
	public Facility validateFacilityCode(String facilityCode) {
		facilityCode = facilityCode.toUpperCase().trim();
		Facility facility = dao.findById(facilityCode);
		return facility;
	}

	@Override
	public void saveFacility(Facility facility) {
		facility.setUid(facility.getUid().toUpperCase());
		put(facility);
	}

	@Override
	public Facility getClosestMatch(String facilityIdentifier) {

		List<Facility> facilities = dao.findAll();

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
		double originLng = 0.0;
		double originLat = 0.0;

		if (originFacility.getLongitudeDecimalDegrees() != null) {
			originLng = originFacility.getLongitudeDecimalDegrees();
		}
		if (originFacility.getLatitudeDecimalDegress() != null) {
			originLat = originFacility.getLatitudeDecimalDegress();
		}

		Facility closestFacility = null;
		double bestDistance = Double.MAX_VALUE;

		// search for the nearest facility using Euclidean distance (as the crow
		// flies)
		// using the facility's coordinates
		for (Facility f : facilitiesWithStock) {
			double destLng = 0.0;
			double destLat = 0.0;
			if (f.getLongitudeDecimalDegrees() != null)
				destLng = f.getLongitudeDecimalDegrees();
			if (f.getLatitudeDecimalDegress() != null)
				destLat = f.getLatitudeDecimalDegress();

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
		return dao.findAllWithoutStockoutOfProduct(product.getUid());
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

	@Override
	public void populateDatabaseFromCSV(File file, String seperator,
			String textDelimeter, FacilityType facilityType) {
		try {
			CSVParser parser = new CSVParser(new FileInputStream(file),
					seperator, textDelimeter);

			for (List<String> row : parser.getRows()) {
				Facility facility = new Facility();

				String name = row.get(0);
				name = name.replaceAll(facilityType.getReadable(), "");

				String location = "";
				String[] locationString;
				// some rows are missing location info
				if (row.size() == 2) {
					location = row.get(1);
					locationString = location.split(",");

					facility.setTown(locationString[0].trim());
					if (locationString.length == 2) {
						facility.setDistrict(locationString[1].trim());
					}
				}

				facility.setOfficialDOHName(name);
				facility.setLocalName(name);
				facility.setUid(generateFacilityCode(name));
				facility.setFacilityType(facilityType);

				saveFacility(facility);
			}

		} catch (FileNotFoundException e) {
			System.err.println("Could not load CSV file: " + file.getPath());
		}
	}

	@Override
	public String generateFacilityCode(String name) {

		name = name.trim();

		int stringPartLength = 3;
		String stringPart = "";

		// capture string part
		if (stringPartLength > name.length()) {
			stringPart = name;
		} else {
			stringPart = name.substring(0, stringPartLength);
		}

		stringPart = stringPart.toUpperCase();

		int numberPart = 1;
		String wholeID = stringPart;

		Facility sameIDFacility = get(wholeID);
		while (sameIDFacility != null) {
			wholeID = stringPart + numberPart;
			numberPart++;
			sameIDFacility = get(wholeID);
		}

		return wholeID;
	}

}
