package za.org.opengov.stockout.dao.medical.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDaoImpl<Product, String> implements
		ProductDao {

	protected ProductDaoImpl() {
		super(Product.class);
	}

	@Override
	public Product getMostCommonStockoutForFacility(String facilityCode) {

		String query = "SELECT sr FROM StockoutReport sr, Product pr "
				+ "WHERE UPPER(sr.facility) LIKE UPPER(:facCode) " 
				+ "GROUP BY sr.product "
				+ "ORDER BY count(*) DESC ";

		StockoutReport report = (StockoutReport) getCurrentSession().createQuery(query)
				.setString("facCode", facilityCode).setMaxResults(1).list()
				.get(0);


		return report.getProduct();
	}

}
