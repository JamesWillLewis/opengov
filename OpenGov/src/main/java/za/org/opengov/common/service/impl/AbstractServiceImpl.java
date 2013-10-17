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
package za.org.opengov.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.dao.AbstractDao;
import za.org.opengov.common.service.AbstractService;

@Transactional
public abstract class AbstractServiceImpl<T_DAO extends AbstractDao<T_ENTITY, T_KEY>, T_ENTITY, T_KEY extends Serializable>
		implements AbstractService<T_ENTITY, T_KEY> {

	protected T_DAO dao;

	public AbstractServiceImpl(T_DAO dao) {
		this.dao = dao;
	}

	@Override
	public T_ENTITY get(T_KEY identifier) {
		return dao.findById(identifier);
	}

	@Override
	public List<T_ENTITY> getAll() {
		return dao.findAll();
	}

	@Override
	public void put(T_ENTITY entity) {
		dao.saveOrUpdate(entity);
	}

	public void remove(T_ENTITY entity) {
		dao.delete(entity);
	}
	
	@Override
	public List<T_ENTITY> getPage(int page, int resultsPerPage) {
		return dao.findPage(page, resultsPerPage);
	}
	
	@Override
	public long getCount() {
		return dao.getCount();
	}

}
