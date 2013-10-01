package za.org.opengov.stockout.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
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

		return (Stockout) criteria.setMaxResults(1).list().get(0);
	}

	@Override
	public Stockout getMostCommonlyReportedStockoutForFacility(String facilityCode) {

		String query = "SELECT sr FROM StockoutReport sr, Product pr, Stockout so "
				+ "WHERE UPPER(sr.stockout.facility) LIKE UPPER(:facCode) " 
				+ "GROUP BY sr.stockout "
				+ "ORDER BY count(*) DESC ";

		StockoutReport stockoutReport = (StockoutReport) getCurrentSession().createQuery(query)
				.setString("facCode", facilityCode).setMaxResults(1).list()
				.get(0);


		return stockoutReport.getStockout();
	}

}
