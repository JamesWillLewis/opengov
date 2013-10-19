package za.org.opengov.stockout.web.admin.domain;

import org.hibernate.validator.constraints.NotEmpty;

import za.org.opengov.stockout.entity.medical.Medicine;

public class MedicineWrapper {

	@NotEmpty
	private String name;

	@NotEmpty
	private String medicineClassUID;

	public MedicineWrapper() {

	}

	public MedicineWrapper(Medicine medicine) {
		
		this.name = medicine.getName();
		if(medicine.getMedicineClass() != null){
		this.medicineClassUID = medicine.getMedicineClass().getUid();}
		else{
			this.medicineClassUID="";
		}
	}

	public String getName() {
		return name;
	}

	public String getMedicineClassUID() {
		return medicineClassUID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMedicineClassUID(String medicineClassUID) {
		this.medicineClassUID = medicineClassUID;
	}

}
