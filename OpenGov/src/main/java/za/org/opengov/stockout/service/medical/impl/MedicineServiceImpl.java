package za.org.opengov.stockout.service.medical.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.medical.MedicineDao;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.service.medical.MedicineService;

@Service("medicineService")
@Transactional
public class MedicineServiceImpl extends AbstractServiceImpl<MedicineDao, Medicine, Long> implements MedicineService {

	@Autowired
	public MedicineServiceImpl(MedicineDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

}
