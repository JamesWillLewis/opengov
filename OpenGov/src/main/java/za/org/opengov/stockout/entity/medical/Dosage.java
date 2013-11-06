/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.org.opengov.stockout.entity.medical;

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
	
	/**
	 * Primary key
	 */
	private Long uid;
	
	/**
	 * Dosage mass/volume in milligrams or milliliters. 
	 */
	private double mass;
	
	/**
	 * Dosage form, such as tablet, injection, syrup, etc.
	 */
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
