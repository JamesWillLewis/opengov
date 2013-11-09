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
package za.org.opengov.stockout.service.medical;

import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;

/**
 * Service (business object) class for {@link Medicine}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface MedicineService extends AbstractService<Medicine, Long> {

	/**
	 * Find medicine of the specified name.
	 * 
	 * @param name Name of the medicine (excluding details/suffix)
	 * @return The matching medicine.
	 */
	public Medicine findByName(String name);
	
	/**
	 * Return all medicines aggregated by the given medicine class.
	 * 
	 * @param medicineClass Name of the medicine class, such as 'antibiotics'.
	 * @return List of medicines.
	 */
	public List<Medicine> getAllMedicinesForClass(MedicineClass medicineClass);
}
