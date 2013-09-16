package za.org.opengov.ussd.service;



public interface UssdDao {
	
	//add data access methods to this

	
	//return Ussdmenu type object, so both request and menu level are altered and returned
	public String getMenu(int menuId);
	
}
