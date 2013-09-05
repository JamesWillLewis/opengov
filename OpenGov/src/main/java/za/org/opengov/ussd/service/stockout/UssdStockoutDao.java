package za.org.opengov.ussd.service.stockout;

import org.springframework.stereotype.Repository;

import za.org.opengov.ussd.service.UssdDao;

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
