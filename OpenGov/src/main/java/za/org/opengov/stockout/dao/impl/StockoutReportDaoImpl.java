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

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.StockoutDao;
import za.org.opengov.stockout.dao.StockoutReportDao;
import za.org.opengov.stockout.entity.StockoutReport;

/**
 * Concrete implementation of {@link StockoutReportDao} DAO.
 *
 * @author James Lewis (james.will.lewis@gmail.com)
 */
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
