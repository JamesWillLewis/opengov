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
package za.org.opengov.stockout.web.domain;

/** Map Marker Stores data to be used for setting a marker in google maps**/
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
