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
package za.org.opengov.common.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import za.org.opengov.common.dao.AbstractDao;

/**
 * Abstract Data Access Object concrete implementation. 
 * @see AbstractDao
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public abstract class AbstractDaoImpl<E, I extends Serializable> implements
		AbstractDao<E, I> {

	private Class<E> entityClass;

	protected AbstractDaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public E findById(I id) {
		return (E) getCurrentSession().get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(E e) {
		getCurrentSession().saveOrUpdate(e);
	}

	@Override
	public void delete(E e) {
		getCurrentSession().delete(e);
	}

	@Override
	public List<E> findByCriteria(Criterion criterion) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		if (criterion != null)
			criteria.add(criterion);
		return criteria.list();
	}

	@Override
	public List<E> findAll() {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		return criteria.list();
	}

	@Override
	public <T> List<T> doQuery(String query, HashMap<String, Object> args) {
		if (args == null) {
			return getCurrentSession().createQuery(query).list();
		} else {
			return getCurrentSession().createQuery(query).setProperties(args)
					.list();
		}
	}
	
	@Override
	public List<E> findPage(int page, int resultsPerPage) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.setFirstResult(resultsPerPage * page);
		criteria.setMaxResults(resultsPerPage);
		//criteria.setFetchSize(resultsPerPage);
		return criteria.list();
	}
	
	@Override
	public long getCount() {
		return (Long) getCurrentSession().createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
	}
}
