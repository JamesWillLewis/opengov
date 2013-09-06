package za.org.opengov.stockout.domain.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.domain.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.domain.dao.medical.MedicineDao;
import za.org.opengov.stockout.domain.entity.medical.Medicine;

@Repository("medicineDao")
public class MedicineDaoImpl extends AbstractDaoImpl<Medicine, Long> implements
		MedicineDao {

	protected MedicineDaoImpl() {
		super(Medicine.class);
	}

}
