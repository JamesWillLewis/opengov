package za.org.opengov.stockout.domain.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.StockoutReportDao;
import za.org.opengov.stockout.domain.entity.StockoutReport;

@Repository("stockoutReportDao")
public class StockoutReportDaoImpl extends AbstractDaoImpl<StockoutReport, Long> implements StockoutReportDao {

	protected StockoutReportDaoImpl() {
		super(StockoutReport.class);
	}

}
