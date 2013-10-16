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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.medical.DosageDao;
import za.org.opengov.stockout.entity.medical.Dosage;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.medical.DosageService;

/**
 * Concrete implementation of {@link DosageService}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Service("dosageService")
@Transactional
public class DosageServiceImpl extends AbstractServiceImpl<DosageDao, Dosage, Long> implements DosageService {

	@Autowired
	public DosageServiceImpl(DosageDao dao) {
		super(dao);
	}

}
