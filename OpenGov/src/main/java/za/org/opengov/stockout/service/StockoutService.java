package za.org.opengov.stockout.service;

import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.Stockout;

public interface StockoutService extends AbstractService<Stockout, Long>{

	public void saveStockout(Stockout stockout);

	public List<Stockout> getAllStockouts();

	public List<Stockout> getAllStockoutsForFacility(String facilityCode);

	public List<Stockout> getAllStockoutsForProduct(String productCode);

	public Stockout getStockout(String facilityCode, String productCode);

	public Stockout getMostCommonlyReportedStockoutForFacility(
			String facilityCode);

	public List<Stockout> getMostCommonlyReportedStockoutsForFacility(
			String facilityCode, int limit);

	public List<Stockout> getAllUnresolvedStockouts();

	public List<Stockout> getMostRecentStockoutsForFacility(
			String facilityCode, int limit);


	public void updateAllStockoutPriorities();

}
