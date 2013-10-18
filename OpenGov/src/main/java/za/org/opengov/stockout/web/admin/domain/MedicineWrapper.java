package za.org.opengov.stockout.web.admin.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class MedicineWrapper {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String medicineClassUID;

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
