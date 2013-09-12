package za.org.opengov.ussd.service;

import za.org.opengov.stockout.ussd.model.UssdMenu;


public interface UssdDao {
	
	//add data access methods to this

	
	//return Ussdmenu type object, so both request and menu level are altered and returned
	public UssdMenu getMenu(String MenuID,String Request);
	
}
