package za.org.opengov.ussd.service.stockout;

import java.util.Properties;

import javax.annotation.Resource;

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
	
	@Resource(name="ussdStockoutEN")
	private Properties menuData;
	
	
	@Override
	public String getMenu(int menuId) {
		return menuData.getProperty("ussd.stockout." + menuId);	
	}


	
}
