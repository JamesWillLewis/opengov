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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.medical.DiseaseDao;
import za.org.opengov.stockout.entity.medical.Disease;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.medical.DiseaseService;

/**
 * Concrete implementation of {@link DiseaseService}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Service("diseaseService")
@Transactional
public class DiseaseServiceImpl extends AbstractServiceImpl<DiseaseDao, Disease, Long> implements DiseaseService {
	
	@Autowired
	public DiseaseServiceImpl(DiseaseDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DiseaseDao diseaseDao;

	@Override
	public List<Disease> getListOfDiseases() {
		return diseaseDao.findAll();
	}
	
	public void saveDisease(Disease disease){
		diseaseDao.saveOrUpdate(disease);
	}

}
