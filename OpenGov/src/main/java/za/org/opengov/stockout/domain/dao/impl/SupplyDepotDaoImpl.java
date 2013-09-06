package za.org.opengov.stockout.domain.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.SupplyDepotDao;
import za.org.opengov.stockout.domain.entity.SupplyDepot;

@Repository("supplyDepotDao")
public class SupplyDepotDaoImpl  extends AbstractDaoImpl<SupplyDepot, Long> implements SupplyDepotDao {

	protected SupplyDepotDaoImpl() {
		super(SupplyDepot.class);
	}

}
