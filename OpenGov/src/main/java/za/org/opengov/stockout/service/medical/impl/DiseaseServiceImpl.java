package za.org.opengov.stockout.service.medical.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.dao.medical.DiseaseDao;
import za.org.opengov.stockout.entity.medical.Disease;
import za.org.opengov.stockout.service.medical.DiseaseService;

@Service("diseaseService")
@Transactional
public class DiseaseServiceImpl implements DiseaseService {
	
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
