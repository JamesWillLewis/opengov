package za.org.opengov.ussd.service;


import javax.servlet.http.HttpSession;

import za.org.opengov.ussd.controller.UssdRequest;
import za.org.opengov.ussd.controller.UssdResponse;

public interface UssdService<REQ extends UssdRequest, RES extends UssdResponse> {

	public RES createUssdResponse(REQ request,HttpSession session);


}
