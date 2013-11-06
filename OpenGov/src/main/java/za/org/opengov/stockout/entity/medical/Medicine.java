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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Domain entity for Medicine concept. Annotated to allow object-relational
 * mapping using JPA/Hibernate.
 * 
 * Represents a Medicine, which represents a particular formulation, and
 * available as particular brands and dosages in the form of a {@link Product}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "SOWS_PHARM_MEDICINE")
public class Medicine {

	/**
	 * Primary key.
	 */
	private Long uid;
	
	/**
	 * Disease which the medicine treats.
	 */
	private Disease disease;
	
	/**
	 * Scientific medical name of the disease. 
	 */
	private String name;
	
	/**
	 * Class of this medicine, such as antiretroviral, antibiotic, etc.
	 */
	private MedicineClass medicineClass;
	
	/**
	 * Particular branded products which are of this formulation. 
	 */
	private Set<Product> products = new HashSet<Product>();

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
	@JoinColumn(name = "FK_DISEASE")
	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "FK_MEDICINE_CLASS")
	public MedicineClass getMedicineClass() {
		return medicineClass;
	}

	public void setMedicineClass(MedicineClass medicineClass) {
		this.medicineClass = medicineClass;
	}

	@OneToMany(mappedBy = "medicine")
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
