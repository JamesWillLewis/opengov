package za.org.opengov.ussd.service.stockout.cm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import za.org.opengov.ussd.service.stockout.UssdStockoutDao;
import za.org.opengov.ussd.web.UssdRequest;
import za.org.opengov.ussd.web.UssdResponse;
import za.org.opengov.ussd.web.cm.CMUssdRequest;
import za.org.opengov.ussd.web.cm.CMUssdResponse;

/**
 * Take note:
 * When a REST call is made, the request will be delegated to the service
 * which matches the name as specified below.
 */
@Service("cm.stockout")
public class CMUssdStockoutServiceImpl implements CMUssdStockoutService {
	
	@Autowired
	private UssdStockoutDao stockoutDao;

	@Override
	public CMUssdResponse createUssdResponse(CMUssdRequest request) {
		CMUssdResponse response = new CMUssdResponse();
		String displayText ="";
		String daoText = "";
		try{
			int menuRequest = Integer.parseInt(request.getRequestid());
			switch(menuRequest){
			//return list of recent stockouts
			case 0: response.setDisplayText(stockoutDao.getMenu(0));//get recent stockouts
					menuRequest++;
					response.setRequestID(Integer.toString(menuRequest));
					break;
			//selection 1-8 = recent stockouts, 9 = display clinic selection
			case 1: if (request.getRequest().equals("9")){
				displayText = "Please enter clinic code or type in name of the clinic \n";
				menuRequest++;
				response.setRequestID(Integer.toString(menuRequest));
				break;
			}
			else{	//display success menu
					// String getDetails = stockoutDao.getMenu(1,request.getRequest()); //query database to get previous user selection(the index within the returned values from the query)
					// displayText = "You have chosen to Report: " + getDetails +
					//		"\n 1.Report Stockout\n 2.Re-enter Report";
					menuRequest=7; //go to success menu options
					response.setRequestID(Integer.toString(menuRequest));
					break;
			}	
					
			case 2: // String daoText = stockoutDao.getMenu(2,request.getRequest()); //dao must support string parameters (user input)
					// if daoText.equals("unavailable");
			}
		}
		catch (NumberFormatException e){
			response.setDisplayText("Please enter a valid response");
		}
		response.setDisplayText(stockoutDao.getMenu(2));
		response.setRequestID(request.getRequestid());
		return response;
	}



	
}
