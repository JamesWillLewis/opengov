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
package za.org.opengov.stockout.dao.impl;

import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.StockoutDao;
import za.org.opengov.stockout.dao.SupplyDepotDao;
import za.org.opengov.stockout.entity.SupplyDepot;

/**
 * Concrete implementation of {@link SupplyDepot} DAO.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Repository("supplyDepotDao")
public class SupplyDepotDaoImpl  extends AbstractDaoImpl<SupplyDepot, Long> implements SupplyDepotDao {

	protected SupplyDepotDaoImpl() {
		super(SupplyDepot.class);
	}

}
