package za.org.opengov.ussd.service.stockout.cm;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import za.org.opengov.stockout.service.StockoutReportService;
import za.org.opengov.ussd.controller.UssdRequest;
import za.org.opengov.ussd.controller.UssdResponse;
import za.org.opengov.ussd.controller.cm.CMUssdRequest;
import za.org.opengov.ussd.controller.cm.CMUssdResponse;
import za.org.opengov.ussd.service.stockout.UssdStockoutDao;
import za.org.opengov.ussd.util.KeyValueStore;

/**
 * Take note: When a REST call is made, the request will be delegated to the
 * service which matches the name as specified below.
 */
@Service("cm.stockout")
public class CMUssdStockoutServiceImpl implements CMUssdStockoutService {
	
	@Autowired
	private KeyValueStore keyValueStore;

	@Autowired
	private UssdStockoutDao stockoutDao;
	
	@Autowired 
	private StockoutReportService stockoutReportService;

	@Override
	public CMUssdResponse createUssdResponse(CMUssdRequest request) {
		
		CMUssdResponse response = new CMUssdResponse();
		String displayText = "";
		String sessionId = "";
		int menuRequest = 0;
		
		try {
			//get the stage at which the user is in,in the ussd session
			menuRequest = Integer.parseInt(request.getRequestid());
			//session id identifies session specific data stored in the key value store
			sessionId = request.getUssdSessionId();
			
			switch (menuRequest) {
			
			case 0:  //welcome message..clinic code prompt
				
				displayText = stockoutDao.getMenu(0);
				++menuRequest;
				
				break;
				
			
			case 1:  //validate clinic and display ussd stock out services
				
				displayText = request.getRequest();
				//stockoutDao.checkClinic(request.getRequest());****database call
				
				if (!displayText.equals("failed")) {
					//Need to set clinic name so that it can be re-used later
					keyValueStore.put("facilityName."+request.getUssdSessionId(), displayText);
					displayText += " " + stockoutDao.getMenu(1);
					++menuRequest;
				
				} else { 
					//displayed failed message and redisplay same menu
					displayText += " " + stockoutDao.getMenu(92);
					throw new NumberFormatException();
				}
				
				break;
				
			case 2: //process service request,get list of recent medicines
				
				displayText = stockoutDao.getMenu(91); //set error message for incorrect string input and invalid integer selection
				
				int requestSelection = Integer.parseInt(request.getRequest());
				
				if (requestSelection>=1 && requestSelection<=3){
					
					displayText = stockoutDao.getMenu(21);
					displayText += "1.Medicine1 \n2.Medicine2 \n3.Medicine3 \n4.Medicine4";
					
					//method that retrieves commonly reported stock out, from a clinic
					//*****Need clinic name entered earlier
					//StockoutDao.retrieveCommonStockouts(keyValueStore.get("facilityName."+request.getUssdSessionId()));
					//**************************************************************
					
					displayText += stockoutDao.getMenu(22);
					
					keyValueStore.put("service."+request.getUssdSessionId(),Integer.toString(requestSelection));	
					++menuRequest;
					
						} else{
							
							//number greater 3 or less than 1 was chosen
							throw new NumberFormatException();
						}
				break;
				
			case 3: //process user selection of recent reports or manual medicine name entry
				
				displayText = stockoutDao.getMenu(91);
				
				int requestMedicine = Integer.parseInt(request.getRequest());
				
				if (requestMedicine>=1 && requestMedicine<=7){ //process medicine selection 1-8
					
					//StockoutDao.retrieveCommonStockoutsIndex(keyValueStore.get("facilityName."+request.getUssdSessionId()),index);
					displayText = "Medicine1";
					keyValueStore.put("medicineName."+sessionId, displayText);
					displayText+= " " + stockoutDao.getMenu(4);
					menuRequest +=2;
					
				} else if(requestMedicine==8){ //display enter medicine name prompt
					
					displayText=stockoutDao.getMenu(3);
					++menuRequest;
				
				} else {//user enters a number less than 1 or greater than 8
					
					throw new NumberFormatException();
					
				}
				break;
				
			case 4: //validate/find nearest match to medicine name+display appropriate menu as above 
				
				//displayText = stockoutDao.CheckAndFindNearestMatch(request.getRequest());
				
				displayText = request.getRequest();
				
				if (!displayText.equals("failed")){ //medicine name found, go to next menu
					
					displayText += " " + stockoutDao.getMenu(4);
					++menuRequest;
				
				} else{ //medicine name not found
					displayText += " " + stockoutDao.getMenu(92);
					throw new NumberFormatException();
				}
				break;
				
			case 5: //run methods for each of the different services+display result.
				
				String serviceRequest = (String) keyValueStore.get("service." + sessionId);
				String medicineName = (String) keyValueStore.get("medicineName." + sessionId);
				String facilityName = (String) keyValueStore.get("facilityName." + sessionId);
				
				int service = Integer.parseInt(serviceRequest);
				int requestOption = Integer.parseInt(request.getRequest());
				
				if (requestOption ==1){
				switch(service){
					case 1: 
						//StockoutDao.reportStockout(medicineName,facilityName);
						displayText = medicineName + " in " + facilityName + " " + stockoutDao.getMenu(5);
						break;
					case 2:
						//displayText = StockoutDao.getStatus(MedicineName,facilityName);
						displayText = stockoutDao.getMenu(6);
						break;
					case 3:
						//displayText = stockoutDao.findNearestNeighbourWithStock(medicineName,facilityName);
						displayText = stockoutDao.getMenu(7);
						break;
					}
				
					menuRequest = 99;
					
				} else if (requestOption==2){
					
					displayText = stockoutDao.getMenu(21);
					displayText += "1.Medicine1 \n2.Medicine2 \n3.Medicine3 \n4.Medicine4";
					
					//method that retrieves commonly reported stock out, from a clinic
					//*****Need clinic name entered earlier
					//StockoutDao.retrieveCommonStockouts(keyValueStore.get("facilityName."+request.getUssdSessionId()));
					//**************************************************************
					
					displayText += stockoutDao.getMenu(22);
					menuRequest=3;

				} else if (requestOption ==3){
					displayText = keyValueStore.get("facilityName." + sessionId) + " " + stockoutDao.getMenu(1);
					menuRequest = 2;
					
				} else{
					
					displayText= stockoutDao.getMenu(91);
					throw new NumberFormatException();
				}
				
				break;
				
			case 99:
				
				keyValueStore.remove("facilityName." + sessionId);
				keyValueStore.remove("service." + sessionId);
				keyValueStore.remove("medicineName." + sessionId);
				keyValueStore.remove("displayText." + sessionId);
				keyValueStore.remove("requestId." + sessionId);
					
			}
		} catch (NumberFormatException e) {
			//reload data from the last request if an error in the users input was detected
			//do not call setResponse as there is no need to overwrite previously saved menu 
			//text with the exact same text. Also avoid concatenating multiple error messages
			
			String strMenuRequest = (String) keyValueStore.get("requestId."+sessionId);
			displayText += (String)keyValueStore.get("displayText."+sessionId);
			response.setDisplayText(displayText);
			response.setRequestID(strMenuRequest);
			
			return response;
		}
		
		//set response once menu logic processing is complete
		setResponse(response, displayText, menuRequest, sessionId);
		
		return response;
		
	}
	
	//set the response object with menu text and menu level(request id), store these variables in
	//key value store so they can be used for error handling if needed.
	private void setResponse(CMUssdResponse response,String menuText,int requestId,String sessionId){
		
		response.setDisplayText(menuText);
		response.setRequestID(Integer.toString(requestId));
		keyValueStore.put("displayText."+sessionId,menuText);
		keyValueStore.put("requestId."+sessionId, Integer.toString(requestId));
	}

}
