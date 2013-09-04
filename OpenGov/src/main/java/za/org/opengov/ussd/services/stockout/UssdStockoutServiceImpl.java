package za.org.opengov.ussd.services.stockout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import za.org.opengov.ussd.channelmobileapi.UssdRequest;
import za.org.opengov.ussd.channelmobileapi.UssdResponse;

/**
 * Take note:
 * When a REST call is made, the request will be delegated to the service
 * which matches the name as specified below.
 */
@Service("stockout")
public class UssdStockoutServiceImpl implements UssdStockoutService {
	
	@Autowired
	private UssdStockoutDao stockoutDao;

	@Override
	public UssdResponse createUssdResponse(UssdRequest ussdRequest) {
		//process the USSD message and generate a response (new menu, etc)
		// The standard XML response
		// Will automatically be converted to XML
		UssdResponse response = new UssdResponse();
		response.setDisplayText("The request was succesfully delegated to the stockout service");
		response.setRequestID(ussdRequest.getRequestid());
		return response;
	}
	
}
