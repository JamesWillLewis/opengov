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
package za.org.opengov.ussd.service.stockout;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import za.org.opengov.ussd.service.UssdDao;

/**
 * USSD data access object for retrieving menu text.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 *
 */
@Repository("ussdStockoutDao")
public class UssdStockoutDao implements UssdDao {
	
	/**
	 * The properties file which contains menu text for the given locale.
	 * 
	 * The default locale chosen is English(EN).
	 */
	@Resource(name="ussdStockoutEN")
	private Properties menuData;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMenu(int menuId) {
		return menuData.getProperty("ussd.stockout." + menuId);	
	}


	
}
