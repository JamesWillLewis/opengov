package za.org.opengov.stockout.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.StockoutReportDao;
import za.org.opengov.stockout.entity.StockoutReport;

@Repository("stockoutReportDao")
public class StockoutReportDaoImpl extends AbstractDaoImpl<StockoutReport, Long> implements StockoutReportDao {

	protected StockoutReportDaoImpl() {
		super(StockoutReport.class);
	}

}
