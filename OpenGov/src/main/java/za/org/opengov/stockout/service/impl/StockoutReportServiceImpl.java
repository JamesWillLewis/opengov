package za.org.opengov.stockout.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.criteria.CriteriaBuilderImpl;
import org.hibernate.internal.CriteriaImpl.CriterionEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.IssueDao;
import za.org.opengov.common.entity.Issue;
import za.org.opengov.common.entity.IssueState;
import za.org.opengov.common.service.IssueService;
import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.dao.StockoutReportDao;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.Subject;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.StockoutReportService;
import za.org.opengov.stockout.service.StockoutService;

@Service("stockoutReportService")
@Transactional
public class StockoutReportServiceImpl implements StockoutReportService {

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
			if(facility == null){
				throw new IllegalArgumentException("No such facility found with code: " + facilityCode);
			}
		} else {
			throw new IllegalArgumentException(
					"Valid facility code must be specified");
		}

		Product product;
		if (productCode != null && !productCode.isEmpty()) {
			product = productDao.findById(productCode);
			if(product == null){
				throw new IllegalArgumentException("No such product found with code: " + productCode);
			}
		} else {
			throw new IllegalArgumentException("Valid product code must be specified");
		}

		StockoutReport stockoutReport = new StockoutReport();
		
		//check whether this stockout has been reported before
		Stockout stockout = stockoutService.getStockout(facilityCode, productCode);
		//this is a new stockout
		if(stockout == null){
			//open an issue
			Issue issue = new Issue();
			issue.setStartTimestamp(Calendar.getInstance().getTime());
			issue.setPriority(0);
			issue.setSeverity(1);
			issue.setState(IssueState.OPEN);
			issueDao.saveOrUpdate(issue);
			
			
			//create a new stockout
			stockout = new Stockout();
			stockout.setFacility(facility);
			stockout.setProduct(product);
			stockout.setResolved(false);
			stockout.setIssue(issue);
			
			stockoutService.saveStockout(stockout);
		} else{
			//is an old stockout, so raise severity
			stockout.getIssue().setSeverity(stockout.getIssue().getSeverity()+1);
			issueDao.saveOrUpdate(stockout.getIssue());
			stockoutService.saveStockout(stockout);
		}

		stockoutReport.setStockout(stockout);
		stockoutReport.setCause(cause);
		//set timestamp to submitted time
		stockoutReport.setTimestamp(Calendar.getInstance().getTime());
		stockoutReport.setReportedToDOH(reportedToDOH);
		stockoutReport.setReporter(reporter);
		stockoutReport.setReportee(reportee);

		return submitStockoutReport(stockoutReport);
	}

	@Override
	public List<StockoutReport> getRecentlyReportedStockouts(int limit) {

		List<StockoutReport> recentReports = stockoutReportDao
				.findMostRecentStockouts(limit);

		return recentReports;
	}

	@Override
	public List<StockoutReport> getRecentlyReportedStockoutsForDisease() {
		throw new NotYetImplementedException();
	}
	



}
