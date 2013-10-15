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
package za.org.opengov.common.service.config;

import za.org.opengov.common.entity.config.SystemParameter;
import za.org.opengov.common.service.AbstractService;

public interface SystemParameterService extends AbstractService<SystemParameter, String>{

	/**
	 * Gets the configuration parameter value of the specified key.
	 * If the parameter does not exist in the database, the default
	 * value is fetched from the configuration properties file.
	 * If no such parameter exists in the configuration properties file,
	 * a value of null is returned.
	 * 
	 * @param key
	 * @return
	 */
	public String getParam(String key);
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setParam(String key, String value);
	
}
