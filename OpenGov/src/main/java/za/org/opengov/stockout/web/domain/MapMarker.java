package za.org.opengov.stockout.web.domain;

public class MapMarker {

private double latitude;
private double longitude;
private String identifier;

public double getLatitude() {
	return latitude;
}
public double getLongitude() {
	return longitude;
}
public String getIdentifier() {
	return identifier;
}
public void setLatitude(double latitude) {
	this.latitude = latitude;
}
public void setLongitude(double longitude) {
	this.longitude = longitude;
}
public void setIdentifier(String identifier) {
	this.identifier = identifier;
}
	
	
}
