package za.org.opengov.stockout.entity;

public enum FacilityType {
	CLINIC("Clinic"), HOSPITAL("Hospital"), PHARMACY("Pharmacy"), OTHER("Other");
	
	private String readable;

	private FacilityType(String readable) {
		this.readable = readable;
	}
	
	public String getReadable() {
		return readable;
	}
	
	public void setReadable(String readable) {
		this.readable = readable;
	}
}
