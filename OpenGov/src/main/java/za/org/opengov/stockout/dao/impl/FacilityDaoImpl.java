package za.org.opengov.stockout.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.FacilityDao;
import za.org.opengov.stockout.entity.Facility;

@Repository("facilityDao")
public class FacilityDaoImpl extends AbstractDaoImpl<Facility, String>
		implements FacilityDao {

	protected FacilityDaoImpl() {
		super(Facility.class);
	}

	@Override
	public List<Facility> findAllWithoutStockoutOfProduct(String uid) {
		Session session = getCurrentSession();

		List<Facility> facilities = session
				.createQuery(
						"select f from Facility f where f not in " +
						"(select s.facility from Stockout s where s.resolved = 0 and s.product = :product)")
				.setString("product", uid).list();

		return facilities;
	}

}
