package za.org.opengov.stockout.domain.dao.medical.impl;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.medical.MedicineDao;
import za.org.opengov.stockout.domain.entity.medical.Medicine;

public class MedicineDaoImpl  extends AbstractDaoImpl<Medicine, Long> implements MedicineDao{

	protected MedicineDaoImpl(Class<Medicine> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
