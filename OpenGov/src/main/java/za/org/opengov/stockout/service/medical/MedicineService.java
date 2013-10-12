package za.org.opengov.stockout.service.medical;

import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;

public interface MedicineService extends AbstractService<Medicine, Long> {

	public Medicine findByName(String name);
	
	public List<Medicine> getAllMedicinesForClass(MedicineClass medicineClass);
}
