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
package za.org.opengov.stockout.dao.medical.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.config.MailingEntryDao;
import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.MedicineClassDao;
import za.org.opengov.stockout.entity.medical.MedicineClass;

/**
 * Concrete implementation of {@link MedicineClassDao} DAO.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Repository("medicineClassDao")
public class MedicineClassDaoImpl extends
		AbstractDaoImpl<MedicineClass, String> implements MedicineClassDao {

	protected MedicineClassDaoImpl() {
		super(MedicineClass.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MedicineClass> fetchAllEager() {
		
		String query = "select distinct c from MedicineClass c left join fetch c.medicines m left join fetch m.products";
		
		
		return getCurrentSession().createQuery(query).list();
	}

}
