package za.org.opengov.stockout.service;

import java.util.List;

import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Product;

public interface StockoutService {

	public void saveStockout(Stockout stockout);
	
	public List<Stockout> getAllStockouts();
	
	public List<Stockout> getAllStockoutsForFacility(String facilityCode);
	
	public List<Stockout> getAllStockoutsForProduct(String productCode);
	
	public Stockout getStockout(String facilityCode, String productCode);
	
	public Stockout getMostCommonlyReportedStockoutForFacility(String facilityCode);
	
}