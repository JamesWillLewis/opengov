/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.org.opengov.stockout.service.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.IssueDao;
import za.org.opengov.common.entity.Issue;
import za.org.opengov.common.entity.IssueState;
import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.dao.StockoutReportDao;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.Subject;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutReportService;
import za.org.opengov.stockout.service.StockoutService;
import za.org.opengov.stockout.service.SubjectService;

/**
 * Concrete implementation of {@link StockoutReportService}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Service("stockoutReportService")
@Transactional
public class StockoutReportServiceImpl extends AbstractServiceImpl<StockoutReportDao, StockoutReport, Long> implements StockoutReportService {

	@Autowired
	public StockoutReportServiceImpl(StockoutReportDao dao) {
		super(dao);
	}

	@Autowired
	private StockoutReportDao stockoutReportDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private FacilityDao facilityDao;

	@Autowired
	private StockoutService stockoutService;

	@Autowired
	private IssueDao issueDao;
	
	@Autowired
	private SubjectService subjectService;

	@Override
	@Transactional(readOnly = false)
	public long submitStockoutReport(StockoutReport stockoutReport) {

		stockoutReportDao.saveOrUpdate(stockoutReport);

		return stockoutReport.getUid();
	}

	@Override
	@Transactional(readOnly = false)
	public long submitStockoutReport(String productCode, String facilityCode,
			Subject reporter, String cause, boolean reportedToDOH)
			throws IllegalArgumentException {

		Facility facility;
		if (facilityCode != null && !facilityCode.isEmpty()) {
			facility = facilityDao.findById(facilityCode);
			if (facility == null) {
				throw new IllegalArgumentException(
						"No such facility found with code: " + facilityCode);
			}
		} else {
			throw new IllegalArgumentException(
					"Valid facility code must be specified");
		}

		Product product;
		if (productCode != null && !productCode.isEmpty()) {
			product = productDao.findById(productCode);
			if (product == null) {
				throw new IllegalArgumentException(
						"No such product found with code: " + productCode);
			}
		} else {
			throw new IllegalArgumentException(
					"Valid product code must be specified");
		}

		StockoutReport stockoutReport = new StockoutReport();

		// check whether this stockout has been reported before
		Stockout stockout = stockoutService.getStockout(facilityCode,
				productCode);
		// this is a new stockout
		if (stockout == null) {
			// open an issue
			Issue issue = new Issue();
			issue.setStartTimestamp(Calendar.getInstance().getTime());
			issue.setPriority(0);
			issue.setSeverity(5);
			issue.setState(IssueState.OPEN);
			issueDao.saveOrUpdate(issue);

			// create a new stockout
			stockout = new Stockout();
			stockout.setFacility(facility);
			stockout.setProduct(product);
			stockout.setResolved(false);
			stockout.setIssue(issue);
		}
		stockout.getStockoutReports().add(stockoutReport);
		stockoutReport.setStockout(stockout);
		stockoutReport.setCause(cause);
		// set timestamp to submitted time
		stockoutReport.setTimestamp(Calendar.getInstance().getTime());
		stockoutReport.setReportedToDOH(reportedToDOH);
		
		//check if reporter has reported before
		Subject originalReporter = subjectService.getSubjectWithContactNumber(reporter.getContactNumber());
		if(originalReporter != null){
			reporter = originalReporter;
		}
		
		stockoutReport.setReporter(reporter);

		return submitStockoutReport(stockoutReport);
	}

	@Override
	public List<StockoutReport> getRecentlyReportedStockouts(int limit) {

		List<StockoutReport> recentReports = stockoutReportDao
				.findMostRecentStockouts(limit);

		return recentReports;
	}


}
