package za.org.opengov.stockout.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.StockoutReportDao;
import za.org.opengov.stockout.entity.StockoutReport;

@Repository("stockoutReportDao")
public class StockoutReportDaoImpl extends AbstractDaoImpl<StockoutReport, Long> implements StockoutReportDao {

	protected StockoutReportDaoImpl() {
		super(StockoutReport.class);
	}

	@Override
	public List<StockoutReport> findMostRecentStockouts(int limit) {
		
		Criteria criteria = getCurrentSession().createCriteria(StockoutReport.class);
		criteria.addOrder(Order.desc("timestamp"));
		criteria.setMaxResults(limit);
		
		return criteria.list();
	}

	@Override
	public List<StockoutReport> findForFacilityAndProduct(String productCode,
			String facilityCode) {
		Criteria criteria = getCurrentSession().createCriteria(StockoutReport.class);
		criteria.createAlias("product", "p");
		criteria.createAlias("facility", "f");
		criteria.add(Restrictions.like("p.uid", productCode.trim()).ignoreCase());
		criteria.add(Restrictions.like("f.uid", facilityCode.trim()).ignoreCase());
		
		return criteria.list();
	}

}
