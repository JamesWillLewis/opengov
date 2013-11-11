/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.org.opengov.ussd.controller.cm;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import za.org.opengov.common.controller.jsp.HomeController;
import za.org.opengov.ussd.controller.UssdController;
import za.org.opengov.ussd.util.KeyValueStore;

/**
 * Provides a USSD web-service using the ChannelMobile protocol.
 * 
 * This service can be easily tested using curl on the command line:
 * eg.
 * 
 * " curl -GET -H "msisdn: 0792784894" -H "provider: Vodacom" -H "ussdSessionId:
 * 3" -H "request: 1" -H "requestid:
 * 123" http://localhost:8080/opengov/ussd/cm/stockout "
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 * 
 */
@Controller
@RequestMapping("ussd/cm")
public class CMUssdController extends
		UssdController<CMUssdRequest, CMUssdResponse> {

	/**
	 * Log4J logger
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	public CMUssdController() {
		super("cm");
	}

	/**
	 * This would be called via a USSD gateway, as an HTTP message, with a GET
	 * method, and various headers with values.
	 * 
	 * The address to call this service would be:
	 * 
	 * [host-address]:8080/opengov/ussd/cm/{service name}
	 * 
	 * For example
	 * 
	 * http://localhost:8080/opengov/ussd/cm/stockout
	 * 
	 * Be sure to include necessary headers.
	 * 
	 * @param ussdRequest
	 * @return Response body, which includes requestID and display text.
	 */
	@RequestMapping(value = "{ussdServiceTag}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody
	CMUssdResponse callService(@RequestHeader(value = "msisdn") String msisdn,
			@RequestHeader(value = "provider") String provider,
			@RequestHeader(value = "ussdSessionId") String ussdSessionID,
			@RequestHeader(value = "request") String request,
			@RequestHeader(value = "requestid") String requestID,
			@PathVariable String ussdServiceTag) {

		// just for testing
		logger.info("USSD service called: " + "\n Invoking USSD service: "
				+ ussdServiceTag + "\n Received cell number: " + msisdn
				+ "\n Provider: " + provider + "\n USSD Session ID: "
				+ ussdSessionID + "\n Request String: " + request
				+ "\n Request ID: " + requestID);

		// construct request object from HTTP headers
		CMUssdRequest ussdRequest = new CMUssdRequest(msisdn, provider,
				ussdSessionID, request, requestID);

		// delegates to service tier
		CMUssdResponse response = delegateToServices(ussdServiceTag,
				ussdRequest);

		return response;
	}
}
