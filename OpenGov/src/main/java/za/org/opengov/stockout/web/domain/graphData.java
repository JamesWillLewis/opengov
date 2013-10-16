package za.org.opengov.stockout.web.domain;

import java.util.List;

public class graphData {
	
	private List<Long> locationStockouts;
	private List<String> locations;
	private List<String> medicines;
	private List<Long> medicineStockouts;
	
	public List<Long> getLocationStockouts() {
		return locationStockouts;
	}
	public List<String> getLocations() {
		return locations;
	}
	public List<String> getMedicines() {
		return medicines;
	}
	public List<Long> getMedicineStockouts() {
		return medicineStockouts;
	}
	public void setLocationStockouts(List<Long> locationStockouts) {
		this.locationStockouts = locationStockouts;
	}
	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
	public void setMedicines(List<String> medicines) {
		this.medicines = medicines;
	}
	public void setMedicineStockouts(List<Long> medicineStockouts) {
		this.medicineStockouts = medicineStockouts;
	}
	

}
