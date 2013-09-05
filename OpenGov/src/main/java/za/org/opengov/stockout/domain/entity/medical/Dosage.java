package za.org.opengov.stockout.domain.entity.medical;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SOWS_PHARM_DOSAGE")
public class Dosage {
	
	private Long uid;
	private double mass;
	private DosageType form;
	
	public Dosage() {
	
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

	@Column(name="MASS")
	public double getMass() {
		return mass;
	}


	public void setMass(double mass) {
		this.mass = mass;
	}

	@Column(name="FORM")
	@Enumerated(EnumType.STRING)
	public DosageType getForm() {
		return form;
	}


	public void setForm(DosageType form) {
		this.form = form;
	}
	
	
	
	
}
