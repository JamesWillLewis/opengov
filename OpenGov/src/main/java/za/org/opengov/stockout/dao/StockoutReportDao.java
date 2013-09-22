package za.org.opengov.stockout.dao;

import java.util.List;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.stockout.entity.StockoutReport;

public interface StockoutReportDao extends AbstractDao<StockoutReport, Long> {

	public List<StockoutReport> findMostRecentStockouts(int limit);

	public List<StockoutReport> findForFacilityAndProduct(String productCode,
			String facilityCode);

}
