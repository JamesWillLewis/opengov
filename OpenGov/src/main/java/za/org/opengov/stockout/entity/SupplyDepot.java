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
package za.org.opengov.stockout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain entity for SupplyDepot concept. Annotated to allow object-relational
 * mapping using JPA/Hibernate.
 * 
 * Represents a SupplyDepot, which provides medical stock to facilities. 
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name="SOWS_SUPPLY_DEPOT")
public class SupplyDepot {

	/**
	 * Primary key
	 */
	private Long uid;
	/**
	 * Name of the supply depot
	 */
	private String name;
	/**
	 * Location of the supply depot.
	 * Not standardized in any way, meaning that it could be in the form
	 * "Cape Town, Western Cape, ZA" or as geographical coordinates.  
	 */
	private String location;
	
	public SupplyDepot() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID", unique = true, nullable = false)
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LOCATION")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
