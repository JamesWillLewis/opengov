package za.org.opengov.stockout.service.medical.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.medical.MedicineClassDao;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.service.medical.MedicineClassService;

@Service("medicineService")
@Transactional
public class MedicineClassDaoImpl extends
		AbstractServiceImpl<MedicineClassDao, MedicineClass, String> implements
		MedicineClassService {

	@Autowired
	public MedicineClassDaoImpl(MedicineClassDao dao) {
		super(dao);
	}
	

}
