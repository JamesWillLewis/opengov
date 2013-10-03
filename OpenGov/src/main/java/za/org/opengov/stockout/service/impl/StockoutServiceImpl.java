package za.org.opengov.stockout.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.IssueService;
import za.org.opengov.stockout.dao.StockoutDao;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.service.StockoutService;

@Service("stockoutService")
@Transactional
public class StockoutServiceImpl implements StockoutService {

	@Autowired
	private StockoutDao stockoutDao;

	@Autowired
	private IssueService issueService;

	@Override
	public List<Stockout> getAllStockouts() {
		return stockoutDao.findAll();
	}

	@Override
	public List<Stockout> getAllStockoutsForFacility(String facilityCode) {
		return stockoutDao.findByFacility(facilityCode);
	}

	@Override
	public List<Stockout> getAllStockoutsForProduct(String productCode) {
		return stockoutDao.findByProduct(productCode);
	}

	@Override
	public Stockout getStockout(String facilityCode, String productCode) {
		return stockoutDao.findByProductAndFacility(productCode, facilityCode);
	}

	@Override
	public void saveStockout(Stockout stockout) {
		stockoutDao.saveOrUpdate(stockout);
	}

	@Override
	public Stockout getMostCommonlyReportedStockoutForFacility(
			String facilityCode) {
		List<Stockout> stockouts = stockoutDao
				.getMostCommonlyReportedStockoutsForFacility(facilityCode, 1);
		if (stockouts.isEmpty()) {
			return null;
		} else {
			return stockouts.get(0);
		}
	}

	@Override
	public List<Stockout> getMostCommonlyReportedStockoutsForFacility(
			String facilityCode, int limit) {
		return stockoutDao.getMostCommonlyReportedStockoutsForFacility(
				facilityCode, limit);
	}

	@Override
	public List<Stockout> getAllUnresolvedStockouts() {
		return stockoutDao.findAllOrderedUnresolvedStockouts();
	}

	@Override
	public List<Stockout> getMostRecentStockoutsForFacility(
			String facilityCode, int limit) {
		return stockoutDao.getStockoutsForFacilityOrderedByTimestamp(
				facilityCode, limit);
	}

	@Override
	public void updateAllStockoutPriorities() {

		List<Stockout> stockouts = stockoutDao.findAll();

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

}
