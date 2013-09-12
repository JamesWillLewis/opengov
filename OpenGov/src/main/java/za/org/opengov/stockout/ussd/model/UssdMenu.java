package za.org.opengov.stockout.ussd.model;

//To process a Ussd Menu, both the request (user input) and the requestId(menu level)
//need to be processed and changed within the same set of menu logic.

public class UssdMenu {

	private String MenuResponse;
	private String MenuId;
	
	
	public UssdMenu(String menuResponse, String menuId) {
		this.MenuResponse = menuResponse;
		this.MenuId = menuId;
	}
	
	public String getMenuResponse() {
		return MenuResponse;
	}
	public void setMenuResponse(String menuResponse) {
		MenuResponse = menuResponse;
	}
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	
}
