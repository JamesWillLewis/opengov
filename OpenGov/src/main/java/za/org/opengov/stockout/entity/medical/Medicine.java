package za.org.opengov.stockout.entity.medical;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="SOWS_PHARM_MEDICINE")
public class Medicine {
	
	private Long uid;
	private Disease disease;
	private String name;
	private MedicineClass medicineClass;

	public Medicine() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID", unique = true, nullable = false)
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@ManyToOne
	@JoinColumn(name="FK_DISEASE")
	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name="FK_MEDICINE_CLASS")
	public MedicineClass getMedicineClass() {
		return medicineClass;
	}
	
	public void setMedicineClass(MedicineClass medicineClass) {
		this.medicineClass = medicineClass;
	}
	
	
}
