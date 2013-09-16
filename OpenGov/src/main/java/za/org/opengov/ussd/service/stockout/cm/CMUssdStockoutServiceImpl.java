package za.org.opengov.ussd.service.stockout.cm;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import za.org.opengov.stockout.service.StockoutReportService;
import za.org.opengov.ussd.controller.UssdRequest;
import za.org.opengov.ussd.controller.UssdResponse;
import za.org.opengov.ussd.controller.cm.CMUssdRequest;
import za.org.opengov.ussd.controller.cm.CMUssdResponse;
import za.org.opengov.ussd.service.stockout.UssdStockoutDao;

/**
 * Take note: When a REST call is made, the request will be delegated to the
 * service which matches the name as specified below.
 */
@Service("cm.stockout")
public class CMUssdStockoutServiceImpl implements CMUssdStockoutService {

	@Autowired
	private UssdStockoutDao stockoutDao;
	
	@Autowired 
	private StockoutReportService stockoutReportService;

	@Override
	public CMUssdResponse createUssdResponse(CMUssdRequest request,HttpSession session) {
		CMUssdResponse response = new CMUssdResponse();
		String displayText = "";
		String daoText = "";
		try {
			int menuRequest = Integer.parseInt(request.getRequestid());
			switch (menuRequest) {
			// return list of recent stockouts
			case 0:
				response.setDisplayText(stockoutDao.getMenu(menuRequest));
				response.setRequestID(Integer.toString(++menuRequest));
				break;
			//validate clinic and display ussd stock out services
			case 1:
				String clinicName = "clinic1";//stockoutDao.checkClinic(request.getRequest());****database call
				if (!clinicName.equals("failed")) {
					
					//**********************************************************
					//Need to set clinic name so that it can be re-used later
					session.setAttribute(request.getUssdSessionId(), clinicName);
					//***********************************************************
					
					response.setDisplayText(clinicName + " "+ stockoutDao.getMenu(1));
					response.setRequestID(Integer.toString(++menuRequest));	
					break;
					
				} else {
					menuRequest--;
					response.setDisplayText("Search for " + request.getRequest() + " failed, Please retry.\n" 
							+ stockoutDao.getMenu(menuRequest));
					response.setRequestID(Integer.toString(++menuRequest));
					break;
				}
			case 2:
				int requestSelection = Integer.parseInt(request.getRequest());
				displayText = "1.Medicine1 \n2.Medicine2 \n3.Medicine3 \n4.Medicine4";
				
				//method that retrieves commonly reported stock out, from a clinic
				//*****Need clinic name entered earlier
				//StockoutDao.retrieveCommonStockouts(stockoutDao.getClinic(session.getAttribute(request.getUssdSessionId())));
				//**************************************************************
				
				//testing the session object
				//**currently works
				displayText+= session.getAttribute(request.getUssdSessionId());
				//*************************
				
				
				displayText+= stockoutDao.getMenu(2);
				if (requestSelection>=1 && requestSelection<=3){			
					response.setRequestID(Integer.toString(menuRequest+requestSelection));
					response.setDisplayText(displayText);
						} else{
							throw new NumberFormatException();
						}
			case 3:
				int requestMedicine = Integer.parseInt(request.getRequest());
				/*if (Request<8){
					
				} else{
					
				}*/
			case 4:
			case 5:
			case 6:
					
			}
		} catch (NumberFormatException e) {
			response.setDisplayText("Please enter a valid response \n" + stockoutDao.getMenu(Integer.parseInt(request.getRequestid())));
			response.setRequestID(request.getRequestid());
		}
		//response.setDisplayText(stockoutDao.getMenu(2,"12345"));
		//response.setDisplayText(stockoutDao.getMenu("2"));
		//response.setRequestID(request.getRequestid());
		
		return response;
	}

}
