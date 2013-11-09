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
import za.org.opengov.stockout.dao.medical.MedicineClassDao;
import za.org.opengov.stockout.entity.StockoutReport;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;

/**
 * Service (business object) class for {@link StockoutReport}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface MedicineClassService extends AbstractService<MedicineClass, String> {

	/**
	 * Eagerly fetch the medicine class entity - in non-technical terms,
	 * this implies the list of medicines which are aggregated by this medicine class
	 * is populated when this method is called, and not lazily when the list if required.
	 * This is required by various controller classes which does not utilize transaction
	 * management which allows for transparent lazy fetches.
	 * 
	 * @return List of medicine classes with medicines loaded.
	 */
	public List<MedicineClass> getMedicineClassesEagerFetch();
	
}
