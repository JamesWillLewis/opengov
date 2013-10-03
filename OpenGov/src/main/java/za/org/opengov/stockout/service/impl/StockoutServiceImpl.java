package za.org.opengov.stockout.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.dao.StockoutDao;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.service.StockoutService;

@Service("stockoutService")
@Transactional
public class StockoutServiceImpl implements StockoutService {

	@Autowired
	private StockoutDao stockoutDao;

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
	public List<Stockout> getMostRecentStockoutsForFacility(String facilityCode, int limit) {
		return stockoutDao.getStockoutsForFacilityOrderedByTimestamp(facilityCode, limit);
	}

}
