package za.org.opengov.stockout.service.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Stockout getMostCommonlyReportedStockoutForFacility(String facilityCode) {
		return stockoutDao.getMostCommonlyReportedStockoutForFacility(facilityCode);
	}

}
