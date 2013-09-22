package za.org.opengov.stockout.service.medical;

import java.util.List;

import za.org.opengov.stockout.entity.medical.Disease;

public interface DiseaseService {

	public List<Disease> getListOfDiseases();
	
	public void saveDisease(Disease disease);

}
