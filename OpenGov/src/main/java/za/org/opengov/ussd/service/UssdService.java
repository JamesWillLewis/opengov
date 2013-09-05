package za.org.opengov.ussd.service;

import za.org.opengov.ussd.web.UssdRequest;
import za.org.opengov.ussd.web.UssdResponse;

public interface UssdService<REQ extends UssdRequest, RES extends UssdResponse> {

	public RES createUssdResponse(REQ request);


}
