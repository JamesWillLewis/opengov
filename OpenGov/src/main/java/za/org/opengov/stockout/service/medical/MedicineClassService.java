package za.org.opengov.stockout.service.medical;

import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.dao.medical.MedicineClassDao;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;


public interface MedicineClassService extends AbstractService<MedicineClass, String> {

	public List<MedicineClass> getMedicineClassesEagerFetch();
	
}
