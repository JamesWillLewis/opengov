package za.org.opengov.stockout.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
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
import za.org.opengov.stockout.service.domain.District;
import za.org.opengov.stockout.service.domain.LocationHeirarchy;
import za.org.opengov.stockout.service.domain.Province;
import za.org.opengov.stockout.service.domain.Town;

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
		facilityIdentifier = facilityIdentifier.toUpperCase();
		// remove unneccesary words from the identifier (such as the facility
		// type, eg. 'clinic')
		for (FacilityType type : FacilityType.values()) {
			facilityIdentifier = facilityIdentifier.replaceAll(type
					.getReadable().toUpperCase(), "");
		}
		facilityIdentifier = facilityIdentifier.trim();

		List<Facility> facilities = dao.findAll();

		StringMatcher matcher = new StringMatcher();

		for (Facility f : facilities) {

			FacilityCodeWrapper codeWrapper = new FacilityCodeWrapper(f);
			FacilityLocalNameWrapper nameWrapper = new FacilityLocalNameWrapper(
					f);
			FacilityDOHNameWrapper dohNameWrapper = new FacilityDOHNameWrapper(
					f);

			// to match against the facility code
			matcher.addStringMatchable(codeWrapper);
			// to match against the official facility name
			matcher.addStringMatchable(dohNameWrapper);
			// only if there is a local name specified and it is different to
			// the official name
			if (f.getLocalName() != null
					&& !f.getLocalName().isEmpty()
					&& !f.getOfficialDOHName().equalsIgnoreCase(
							f.getLocalName())) {
				matcher.addStringMatchable(nameWrapper);
			}
		}
		StringMatchable matchable = matcher.getClosestMatch(facilityIdentifier);

		if (matchable instanceof FacilityCodeWrapper) {
			return ((FacilityCodeWrapper) matchable).getFacility();
		} else if (matchable instanceof FacilityLocalNameWrapper) {
			return ((FacilityLocalNameWrapper) matchable).getFacility();
		} else if (matchable instanceof FacilityDOHNameWrapper) {
			return ((FacilityDOHNameWrapper) matchable).getFacility();
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
				facility.setProvince("Western Cape");

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

	@Override
	public List<String> listAllProvinces() {
		return dao.doQuery("select distinct s.province from Facility s", null);
	}

	@Override
	public List<String> listAllDistrictsForProvince(String provinceName) {
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("province", provinceName);
		return dao
				.doQuery(
						"select distinct s.district from Facility s where s.province like :province",
						args);
	}

	@Override
	public List<String> listAllTownsForDistrict(String districtName) {
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("district", districtName);
		return dao
				.doQuery(
						"select distinct s.town from Facility s where s.district like :district",
						args);
	}

	@Override
	public List<Facility> listAllFacilitiesForTown(String townName) {
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("town", townName);
		return dao.doQuery("select f from Facility f where f.town like :town",
				args);
	}

	@Override
	public long totalStockoutsForProvince(String provinceName) {
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("province", provinceName);
		return (Long) dao
				.doQuery(
						"select count(s) from Stockout s where s.facility.province like :province",
						args).get(0);
	}

	@Override
	public long totalStockoutsForDistrict(String districtName) {
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("district", districtName);
		return (Long) dao
				.doQuery(
						"select count(s) from Stockout s where s.facility.district like :district",
						args).get(0);
	}

	@Override
	public long totalStockoutsForTown(String townName) {
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("town", townName);
		return (Long) dao
				.doQuery(
						"select count(s) from Stockout s where s.facility.town like :town",
						args).get(0);
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

	private class FacilityLocalNameWrapper implements StringMatchable {

		private Facility facility;

		public FacilityLocalNameWrapper(Facility facility) {
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

	private class FacilityDOHNameWrapper implements StringMatchable {

		private Facility facility;

		public FacilityDOHNameWrapper(Facility facility) {
			this.facility = facility;
		}

		public Facility getFacility() {
			return facility;
		}

		@Override
		public String getStringToMatch() {
			return facility.getOfficialDOHName();
		}

	}

	@Override
	public LocationHeirarchy getLocationHeirarchy() {
		LocationHeirarchy locationHeirarchy = new LocationHeirarchy();
		
		for(String provinceName: listAllProvinces()){
			Province province = new Province(provinceName, locationHeirarchy);
			locationHeirarchy.addProvince(province);
			if(provinceName==null){
				provinceName = "";
			}
			
			for(String districtName: listAllDistrictsForProvince(provinceName)){
				District district = new District(districtName, province);
				province.addDistrict(district);
				if(districtName == null){
					districtName = "";
				}
				
				for(String townName: listAllTownsForDistrict(districtName)){
					Town town = new Town(townName, district);
					district.addTown(town);
					if(townName == null){
						townName = "";
					}
					
					for(Facility facility: listAllFacilitiesForTown(townName)){
						town.addFacility(facility);
					}
					
				}
			}
			
		}
		
		return locationHeirarchy;
	}

	@Override
	public List<Facility> listAllFacilitiesForProvince(String provinceName) {
		return dao.findByCriteria(Restrictions.like("province", provinceName));
	}

}
