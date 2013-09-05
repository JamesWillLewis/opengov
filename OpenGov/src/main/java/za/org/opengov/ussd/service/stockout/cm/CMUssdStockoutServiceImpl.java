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
		response.setDisplayText(stockoutDao.getMenu(2));
		response.setRequestID(request.getRequestid());
		return response;
	}



	
}
