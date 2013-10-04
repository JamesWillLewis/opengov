package za.org.opengov.stockout.entity.medical;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SOWS_PHARM_MEDICINE_CLASS")
public class MedicineClass {

	private String uid;
	
	public MedicineClass() {
	}
	
	@Id
	@Column(name="UID", unique = true, nullable = false)
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
}
