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
package za.org.opengov.common.entity.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain entity for System Parameter concept. Annotated to allow
 * object-relational mapping using JPA/Hibernate.
 * 
 * A system parameter allows various system-wide options to be persisted, as a
 * key-value pair. Generally, a system option should following a proper
 * hierarchical naming convention, such as 'stockout.notification.enabled'. Each
 * system parameter should have it's default value defined in the
 * configuration_defaults.properties file.
 * 
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "OPENGOV_PARAMS")
public class SystemParameter {

	/**
	 * Primary key.
	 * Name of the parameter, such as 'stockout.notification.enabled'
	 */
	private String parameter;
	/**
	 * Value of the parameter, such as 'enabled', 'true', '1', etc.
	 */
	private String value;

	public SystemParameter() {
	}

	@Id
	@Column(name = "PARAM", unique = true, nullable = false)
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
