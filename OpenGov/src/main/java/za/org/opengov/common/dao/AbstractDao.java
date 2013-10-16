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
package za.org.opengov.common.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;

import za.org.opengov.stockout.entity.Stockout;

/**
 * Abstract Data Access Object interface.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface AbstractDao<E, I extends Serializable> {

	/**
	 * Return item which corresponds to the specified UID.
	 * 
	 * @param id
	 *            UID
	 * @return Item referenced by the UID
	 */
	E findById(I id);

	/**
	 * Save or update (persist to database) the given item.
	 * 
	 * @param e
	 *            Item to persist
	 */

	void saveOrUpdate(E e);

	/**
	 * Remove (permanently) the specified item from the database.
	 * 
	 * @param e
	 *            Item to delete
	 */
	void delete(E e);

	/**
	 * Return a set of items corresponding to the given criterion.
	 * 
	 * @param criterion
	 *            Criterion to filter the items
	 * @return List of items
	 */
	List<E> findByCriteria(Criterion criterion);

	/**
	 * Return list of all items.
	 * 
	 * @return List of all items
	 */
	List<E> findAll();

	/**
	 * Return a paginated selection of items from the database. Will return
	 * resultsPerPage elements of the specified page.
	 * 
	 * @param page
	 *            Page index (starts from page 0)
	 * @param resultsPerPage
	 *            Number of items per page
	 * @return List of items for the specified page
	 */
	List<E> findPage(int page, int resultsPerPage);

	/**
	 * Perform the given query (in HQL, not SQL) on the database
	 * and return the results. Parameters of the query are given
	 * as a hash-map of parameter (key) to argument (value).
	 * 
	 * @param query
	 * @param args
	 * @return
	 */
	<T> List<T> doQuery(String query, HashMap<String, Object> args);

}