package za.org.opengov.stockout.dao.medical.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.MedicineClassDao;
import za.org.opengov.stockout.entity.medical.MedicineClass;

@Repository("medicineClassDao")
public class MedicineClassDaoImpl extends
		AbstractDaoImpl<MedicineClass, String> implements MedicineClassDao {

	protected MedicineClassDaoImpl() {
		super(MedicineClass.class);
	}

}
