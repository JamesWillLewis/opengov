package za.org.opengov.stockout.entity.medical;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SOWS_PHARM_MEDICINE_CLASS")
public class MedicineClass {

	private String uid;
	private Set<Medicine> medicines = new HashSet<Medicine>();

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
	
	@OneToMany(mappedBy="medicineClass")
	public Set<Medicine> getMedicines() {
		return medicines;
	}
	
	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}
}
