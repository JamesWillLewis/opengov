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
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import za.org.opengov.stockout.service.domain.Province;

@Entity
@Table(name="SOWS_PHARM_MEDICINE_CLASS")
public class MedicineClass {

	private String uid;
	private Set<Medicine> medicines;

	public MedicineClass() {
		this.medicines = new HashSet<Medicine>();
	}
	
	public MedicineClass(Set<Medicine> medicines) {
		this.medicines = medicines;
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
