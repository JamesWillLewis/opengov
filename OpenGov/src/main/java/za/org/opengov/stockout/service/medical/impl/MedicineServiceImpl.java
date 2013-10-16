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
package za.org.opengov.stockout.service.medical.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.medical.MedicineDao;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.medical.MedicineService;

/**
 * Concrete implementation of {@link MedicineService}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Service("medicineService")
@Transactional
public class MedicineServiceImpl extends
		AbstractServiceImpl<MedicineDao, Medicine, Long> implements
		MedicineService {

	@Autowired
	public MedicineServiceImpl(MedicineDao dao) {
		super(dao);
	}

	@Override
	public Medicine findByName(String name) {
		List<Medicine> medicines = dao.findByCriteria(Restrictions.like("name",
				name));
		if (medicines.isEmpty()) {
			return null;
		} else {
			return medicines.get(0);
		}
	}

	@Override
	public List<Medicine> getAllMedicinesForClass(MedicineClass medicineClass) {
		return dao.findByCriteria(Restrictions.eq("medicineClass", medicineClass));
	}

}
