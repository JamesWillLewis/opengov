package za.org.opengov.stockout.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.dao.StockoutReportDao;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.Subject;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.StockoutReportService;

@Service("stockoutReportService")
@Transactional
public class StockoutReportServiceImpl implements StockoutReportService {

	@Autowired
	private StockoutReportDao stockoutReportDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private FacilityDao facilityDao;

	@Override
	@Transactional(readOnly = false)
	public long submitStockoutReport(StockoutReport stockoutReport) {

		stockoutReportDao.saveOrUpdate(stockoutReport);

		return stockoutReport.getUid();
	}

	@Override
	@Transactional(readOnly = false)
	public long submitStockoutReport(String productCode, String facilityCode,
			Subject reporter, Subject reportee, String cause,
			boolean reportedToDOH, boolean resolved)
			throws IllegalArgumentException {

		Facility facility;
		if (facilityCode != null && !facilityCode.isEmpty()) {
			facility = facilityDao.findById(facilityCode);
		} else {
			throw new IllegalArgumentException(
					"Facility code must be specified");
		}

		Product product;
		if (productCode != null && !productCode.isEmpty()) {
			product = productDao.findById(productCode);
		} else {
			throw new IllegalArgumentException("Product code must be specified");
		}

		StockoutReport stockoutReport = new StockoutReport();

		stockoutReport.setCause(cause);
		stockoutReport.setFacility(facility);
		stockoutReport.setProduct(product);
		stockoutReport.setReportedToDOH(reportedToDOH);
		stockoutReport.setResolved(resolved);
		stockoutReport.setReporter(reporter);
		stockoutReport.setReportee(reportee);

		return submitStockoutReport(stockoutReport);
	}


}
