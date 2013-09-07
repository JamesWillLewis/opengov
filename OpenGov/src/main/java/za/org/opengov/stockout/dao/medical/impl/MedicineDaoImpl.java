package za.org.opengov.stockout.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.MedicineDao;
import za.org.opengov.stockout.entity.medical.Medicine;

@Repository("medicineDao")
public class MedicineDaoImpl extends AbstractDaoImpl<Medicine, Long> implements
		MedicineDao {

	protected MedicineDaoImpl() {
		super(Medicine.class);
	}

}
