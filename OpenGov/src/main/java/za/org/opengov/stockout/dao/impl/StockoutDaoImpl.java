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
package za.org.opengov.stockout.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.config.MailingEntryDao;
import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.StockoutDao;
import za.org.opengov.stockout.entity.Stockout;
import za.org.opengov.stockout.entity.StockoutReport;

/**
 * Concrete implementation of {@link StockoutDao} DAO.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Repository("stockoutDao")
public class StockoutDaoImpl extends AbstractDaoImpl<Stockout, Long> implements
		StockoutDao {

	protected StockoutDaoImpl() {
		super(Stockout.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> findByFacility(String facilityCode) {
		Criteria criteria = getCurrentSession().createCriteria(Stockout.class);

		criteria.createAlias("facility", "f");
		criteria.add(Restrictions.like("f.uid", facilityCode).ignoreCase());
		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> findByProduct(String productCode) {
		Criteria criteria = getCurrentSession().createCriteria(Stockout.class);

		criteria.createAlias("product", "p");
		criteria.add(Restrictions.like("p.uid", productCode).ignoreCase());
		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stockout> findAllOrderedUnresolvedStockouts() {
		Criteria criteria = getCurrentSession().createCriteria(Stockout.class);
		criteria.add(Restrictions.eq("resolved", false));
		criteria.createAlias("issue", "i");
		criteria.addOrder(Order.asc("i.priority"));
		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
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
