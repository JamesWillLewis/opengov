package za.org.opengov.stockout.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.StockoutDao;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;

@Repository("stockoutDao")
public class StockoutDaoImpl extends AbstractDaoImpl<Stockout, Long> implements
		StockoutDao {

	protected StockoutDaoImpl() {
		super(Stockout.class);
	}

	@Override
	public List<Stockout> findByFacility(String facilityCode) {
		Criteria criteria = getCurrentSession().createCriteria(Stockout.class);

		criteria.createAlias("facility", "f");
		criteria.add(Restrictions.like("f.uid", facilityCode).ignoreCase());
		return criteria.list();
	}

	@Override
	public List<Stockout> findByProduct(String productCode) {
		Criteria criteria = getCurrentSession().createCriteria(Stockout.class);

		criteria.createAlias("product", "p");
		criteria.add(Restrictions.like("p.uid", productCode).ignoreCase());
		return criteria.list();
	}

	@Override
	public Stockout findByProductAndFacility(String productCode,
			String facilityCode) {
		Criteria criteria = getCurrentSession().createCriteria(Stockout.class);

		criteria.createAlias("facility", "f");
		criteria.createAlias("product", "p");

		criteria.add(Restrictions.like("f.uid", facilityCode).ignoreCase());
		criteria.add(Restrictions.like("p.uid", productCode).ignoreCase());

		List<Stockout> stockouts = criteria.setMaxResults(1).list();
		if (stockouts.isEmpty()) {
			return null;
		} else {
			return (Stockout) stockouts.get(0);
		}
	}

	@Override
	public List<Stockout> getMostCommonlyReportedStockoutsForFacility(
			String facilityCode, int limit) {

		String query = "SELECT sr.stockout FROM StockoutReport sr "
				+ "WHERE UPPER(sr.stockout.facility) LIKE UPPER(:facCode) "
				+ "GROUP BY sr.stockout " + "ORDER BY count(*) DESC ";

		List<Stockout> stockoutReports = getCurrentSession()
				.createQuery(query).setString("facCode", facilityCode)
				.setMaxResults(limit).list();

		return stockoutReports;
	}

	@Override
	public List<Stockout> findAllOrderedUnresolvedStockouts() {
		Criteria criteria = getCurrentSession().createCriteria(Stockout.class);
		criteria.add(Restrictions.eq("resolved", 0));
		criteria.createAlias("issue", "i");
		criteria.addOrder(Order.asc("i.priority"));
		return criteria.list();
	}

	@Override
	public List<Stockout> getStockoutsForFacilityOrderedByTimestamp(
			String facilityCode, int limit) {
		Criteria criteria = getCurrentSession().createCriteria(Stockout.class);
		criteria.createAlias("issue", "i");
		criteria.addOrder(Order.desc("i.startTimestamp"));
		criteria.setMaxResults(limit);
		
		return criteria.list();
	}

}
