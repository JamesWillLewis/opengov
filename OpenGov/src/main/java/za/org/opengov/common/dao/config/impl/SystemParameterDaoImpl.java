package za.org.opengov.common.dao.config.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.config.SystemParameterDao;
import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.common.entity.config.SystemParameter;

@Repository
public class SystemParameterDaoImpl extends AbstractDaoImpl<SystemParameter, String> implements SystemParameterDao{

	protected SystemParameterDaoImpl() {
		super(SystemParameter.class);
	}

}
