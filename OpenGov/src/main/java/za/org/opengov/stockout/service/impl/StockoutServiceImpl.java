/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.org.opengov.stockout.service.impl;

import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.IssueService;
import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.StockoutDao;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutService;

/**
 * Concrete implementation of {@link StockoutService}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Service("stockoutService")
@Transactional
public class StockoutServiceImpl extends
		AbstractServiceImpl<StockoutDao, Stockout, Long> implements
		StockoutService {

	@Autowired
	public StockoutServiceImpl(StockoutDao dao) {
		super(dao);
	}

	@Autowired
	private IssueService issueService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getAllStockouts() {
		return dao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getAllStockoutsForFacility(String facilityCode) {
		return dao.findByFacility(facilityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getAllStockoutsForProduct(String productCode) {
		return dao.findByProduct(productCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Stockout getStockout(String facilityCode, String productCode) {
		return dao.findByProductAndFacility(productCode, facilityCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveStockout(Stockout stockout) {
		dao.saveOrUpdate(stockout);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Stockout getMostCommonlyReportedStockoutForFacility(
			String facilityCode) {
		List<Stockout> stockouts = dao
				.getMostCommonlyReportedStockoutsForFacility(facilityCode, 1);
		if (stockouts.isEmpty()) {
			return null;
		} else {
			return stockouts.get(0);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getMostCommonlyReportedStockoutsForFacility(
			String facilityCode, int limit) {
		return dao.getMostCommonlyReportedStockoutsForFacility(facilityCode,
				limit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getAllUnresolvedStockouts() {
		return dao.findAllOrderedUnresolvedStockouts();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getMostRecentStockoutsForFacility(
			String facilityCode, int limit) {
		return dao.getStockoutsForFacilityOrderedByTimestamp(facilityCode,
				limit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateAllStockoutPriorities() {

		List<Stockout> stockouts = dao.findAll();

		int minSev, maxSev, minOcc, maxOcc, minDur, maxDur;
		minSev = Integer.MAX_VALUE;
		minOcc = Integer.MAX_VALUE;
		minDur = Integer.MAX_VALUE;

		maxSev = Integer.MIN_VALUE;
		maxOcc = Integer.MIN_VALUE;
		maxDur = Integer.MIN_VALUE;

		// determine max and min severity, occurances & duration
		for (Stockout stockout : stockouts) {

			// stockouts which haven't been assigned as an issue must be ignored
			if (stockout.getIssue() != null) {

				int severity = stockout.getIssue().getSeverity();
				int occurances = stockout.getStockoutReports().size();
				DateTime stockoutDate = new DateTime(stockout.getIssue()
						.getStartTimestamp());
				int duration = Days.daysBetween(stockoutDate, DateTime.now())
						.getDays();

				if (severity < minSev) {
					minSev = severity;
				}
				if (severity > maxSev) {
					maxSev = severity;
				}
				if (occurances < minOcc) {
					minOcc = occurances;
				}
				if (occurances > maxOcc) {
					maxOcc = occurances;
				}
				if (duration < minDur) {
					minDur = duration;
				}
				if (duration > maxDur) {
					maxDur = duration;
				}
			}
		}

		// assign priorities
		for (Stockout stockout : stockouts) {
			// stockouts which haven't been assigned as an issue must be ignored
			if (stockout.getIssue() != null) {

				int severity = stockout.getIssue().getSeverity();
				int occurances = stockout.getStockoutReports().size();
				DateTime stockoutDate = new DateTime(stockout.getIssue()
						.getStartTimestamp());
				int duration = Days.daysBetween(stockoutDate, DateTime.now())
						.getDays();

				int priority = issueService.calculatePriority(severity,
						occurances, duration, minSev, minOcc, minDur, maxSev,
						maxOcc, maxDur);

				stockout.getIssue().setPriority(priority);
				issueService.put(stockout.getIssue());

			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getStockoutsForProvince(String provinceName) {
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("province", provinceName);
		return dao
				.doQuery(
						"select s from Stockout s where s.facility.province like :province",
						args);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getStockoutsForDistrict(String districtName) {
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("district", districtName);
		return dao
				.doQuery(
						"select s from Stockout s where s.facility.district like :district",
						args);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getStockoutsForTown(String townName) {
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("town", townName);
		return dao.doQuery(
				"select s from Stockout s where s.facility.town like :town",
				args);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getStockoutsForMedicine(Medicine medicine) {
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("medName", medicine.getUid());
		return dao
				.doQuery(
						"select s from Stockout s where s.product.medicine.uid = :medName",
						args);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> getStockoutsForMedicineClass(
			MedicineClass medicineClass) {
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("medClassName", medicineClass.getUid());
		return dao
				.doQuery(
						"select s from Stockout s where s.product.medicine.medicineClass.uid like :medClassName",
						args);
	}

}
