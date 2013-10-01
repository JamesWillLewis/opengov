package za.org.opengov.stockout.dao;

import java.util.List;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.stockout.entity.Facility;

public interface FacilityDao extends AbstractDao<Facility, String> {

	public List<Facility> findAllWithoutStockoutOfProduct(String uid);

}
