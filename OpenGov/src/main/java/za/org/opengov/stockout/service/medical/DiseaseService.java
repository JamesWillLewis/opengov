package za.org.opengov.stockout.service.medical;

import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.medical.Disease;

public interface DiseaseService extends AbstractService<Disease, Long> {

	public List<Disease> getListOfDiseases();
	
	public void saveDisease(Disease disease);

}
