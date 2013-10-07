package za.org.opengov.stockout.service.medical;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.medical.Medicine;

public interface MedicineService extends AbstractService<Medicine, Long> {

	public Medicine findByName(String name);
	

}
