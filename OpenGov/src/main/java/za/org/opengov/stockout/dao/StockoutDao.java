package za.org.opengov.stockout.dao;

import java.util.List;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.medical.Product;

public interface StockoutDao extends AbstractDao<Stockout, Long> {

	public List<Stockout> findByFacility(String facilityCode);

	public List<Stockout> findByProduct(String productCode);

	public Stockout findByProductAndFacility(String productCode,
			String facilityCode);
	
	public Stockout getMostCommonlyReportedStockoutForFacility(String facilityCode);
	

}
