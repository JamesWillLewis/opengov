package za.org.opengov.stockout.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.SupplyDepotDao;
import za.org.opengov.stockout.entity.SupplyDepot;
import za.org.opengov.stockout.service.SupplyDepotService;

@Service("supplyDepotService")
@Transactional
public class SupplyDepotServiceImpl extends AbstractServiceImpl<SupplyDepotDao, SupplyDepot, Long> implements SupplyDepotService {

	@Autowired
	public SupplyDepotServiceImpl(SupplyDepotDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

}
