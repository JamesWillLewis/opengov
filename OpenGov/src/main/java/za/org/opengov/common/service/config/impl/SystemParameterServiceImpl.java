package za.org.opengov.common.service.config.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.org.opengov.common.dao.config.SystemParameterDao;
import za.org.opengov.common.entity.config.SystemParameter;
import za.org.opengov.common.service.config.SystemParameterService;
import za.org.opengov.common.service.impl.AbstractServiceImpl;

@Service
public class SystemParameterServiceImpl extends AbstractServiceImpl<SystemParameterDao, SystemParameter, String> 
implements SystemParameterService{

	@Autowired
	public SystemParameterServiceImpl(SystemParameterDao dao) {
		super(dao);
	}

}
