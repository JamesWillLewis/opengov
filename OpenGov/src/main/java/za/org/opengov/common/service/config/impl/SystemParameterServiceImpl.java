package za.org.opengov.common.service.config.impl;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.org.opengov.common.dao.config.SystemParameterDao;
import za.org.opengov.common.entity.config.SystemParameter;
import za.org.opengov.common.service.config.SystemParameterService;
import za.org.opengov.common.service.impl.AbstractServiceImpl;

@Service
public class SystemParameterServiceImpl extends
		AbstractServiceImpl<SystemParameterDao, SystemParameter, String>
		implements SystemParameterService {

	@Resource(name = "configurationDefaults")
	private Properties configurationDefaults;

	@Autowired
	public SystemParameterServiceImpl(SystemParameterDao dao) {
		super(dao);
	}

	@Override
	public String getParam(String key) {
		SystemParameter parameter = dao.findById(key);
		if (parameter == null) {
			String value = configurationDefaults.getProperty(key);
			return value;
		} else {
			return parameter.getValue();
		}
	}

	@Override
	public void setParam(String key, String value) {
		if (key != null) {
			SystemParameter parameter = dao.findById(key);
			if (parameter == null) {
				parameter = new SystemParameter();
				parameter.setParameter(key);
				parameter.setValue(value);
			} else {
				parameter.setValue(value);
			}
			dao.saveOrUpdate(parameter);
		} else {
			throw new IllegalArgumentException("Key must not be null");
		}
	}

}
