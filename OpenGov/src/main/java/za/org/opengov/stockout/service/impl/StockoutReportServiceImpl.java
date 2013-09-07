package za.org.opengov.stockout.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.service.StockoutReportService;

@Service("stockoutReportService")
@Transactional
public class StockoutReportServiceImpl implements StockoutReportService {

	@Override
	public long submitStockoutReport(StockoutReport stockoutReport) {

		return 0;
	}
	
	

}
