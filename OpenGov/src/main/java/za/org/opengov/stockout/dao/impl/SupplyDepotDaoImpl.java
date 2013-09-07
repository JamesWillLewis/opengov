package za.org.opengov.stockout.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.SupplyDepotDao;
import za.org.opengov.stockout.entity.SupplyDepot;

@Repository("supplyDepotDao")
public class SupplyDepotDaoImpl  extends AbstractDaoImpl<SupplyDepot, Long> implements SupplyDepotDao {

	protected SupplyDepotDaoImpl() {
		super(SupplyDepot.class);
	}

}
