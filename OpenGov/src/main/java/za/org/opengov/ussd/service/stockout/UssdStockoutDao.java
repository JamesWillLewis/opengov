package za.org.opengov.ussd.service.stockout;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import za.org.opengov.stockout.ussd.model.UssdMenu;
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
	
	//private UssdMenu ussdMenuResponse;
	
	@Override
	public UssdMenu getMenu(String MenuID,String Request) {
		//QUESTION??
		//create new object upon return or create object prior to method call
		//and use set parameters??
		return new UssdMenu(menuData.getProperty("ussd.stockout." + MenuID),MenuID);
		
		/*String displayText = "";
		String daoText = "";
		try {
			int menuRequest = Integer.parseInt(request.getRequestid());
			switch (menuRequest) {
			// return list of recent stockouts
			case 0:
				response.setDisplayText(stockoutDao.getMenu(0));// get recent
																// stockouts
				menuRequest++;
				response.setRequestID(Integer.toString(menuRequest));
				break;
			// selection 1-8 = recent stockouts, 9 = display clinic selection
			case 1:
				if (request.getRequest().equals("9")) {
					displayText = "Please enter clinic code or type in name of the clinic \n";
					menuRequest++;
					response.setRequestID(Integer.toString(menuRequest));
					break;
				} else { // display success menu
							// String getDetails =
							// stockoutDao.getMenu(1,request.getRequest());
							// //query database to get previous user
							// selection(the index within the returned values
							// from the query)
							// displayText = "You have chosen to Report: " +
							// getDetails +
							// "\n 1.Report Stockout\n 2.Re-enter Report";
					menuRequest = 7; // go to success menu options
					response.setRequestID(Integer.toString(menuRequest));
					break;
				}

			case 2: // String daoText =
					// stockoutDao.getMenu(2,request.getRequest()); //dao must
					// support string parameters (user input)
					// if daoText.equals("unavailable");
			}
		} catch (NumberFormatException e) {
			response.setDisplayText("Please enter a valid response");
		}*/
	}
	
}
