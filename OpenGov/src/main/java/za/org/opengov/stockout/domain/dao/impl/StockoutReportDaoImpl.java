package za.org.opengov.stockout.domain.dao.impl;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.StockoutReportDao;
import za.org.opengov.stockout.domain.entity.StockoutReport;

public class StockoutReportDaoImpl extends AbstractDaoImpl<StockoutReport, Long> implements StockoutReportDao {

	protected StockoutReportDaoImpl(Class<StockoutReport> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
