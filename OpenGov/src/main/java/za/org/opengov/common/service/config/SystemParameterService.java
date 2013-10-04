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
