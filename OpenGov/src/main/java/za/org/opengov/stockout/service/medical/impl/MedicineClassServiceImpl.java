package za.org.opengov.stockout.service.medical.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.medical.MedicineClassDao;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.service.medical.MedicineClassService;

@Service("medicineClassService")
@Transactional
public class MedicineClassServiceImpl extends
		AbstractServiceImpl<MedicineClassDao, MedicineClass, String> implements
		MedicineClassService {

	@Autowired
	public MedicineClassServiceImpl(MedicineClassDao dao) {
		super(dao);
	}
	
	public List<MedicineClass> getMedicineClassesEagerFetch(){
		return dao.fetchAllEager();
	}
	

}
