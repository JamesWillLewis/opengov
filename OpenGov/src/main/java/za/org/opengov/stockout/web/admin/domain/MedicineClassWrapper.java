package za.org.opengov.stockout.web.admin.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class MedicineClassWrapper {

	@NotEmpty
	private String uid;

	public MedicineClassWrapper() {

	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
