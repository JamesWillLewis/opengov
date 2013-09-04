package za.org.opengov.ussd.services;

import org.springframework.stereotype.Repository;

import za.org.opengov.ussd.UssdDao;

/**
 * Data for the service will be found here
 * 
 * Still a WIP
 * 
 * @author james
 *
 */
@Repository("ussdStockoutDao")
public class UssdStockoutDao implements UssdDao {

	@Override
	public String getMenu(int id) {
		
		return null;
	}
	
}
