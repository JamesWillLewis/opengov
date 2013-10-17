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
package za.org.opengov.common.service;

import java.io.Serializable;
import java.util.List;

public interface AbstractService<E, I extends Serializable> {

	public E get(I identifier);
	
	public void remove(E entity);
	
	public List<E> getAll();
	
	public void put(E entity);
	
	public List<E> getPage(int page, int resultsPerPage);
	
	public long getCount();
	
}
