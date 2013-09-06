package za.org.opengov.ussd.web.cm;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import za.org.opengov.ussd.web.UssdController;

/**
 * 
 * This can be easily tested using curl on the command line:
 * 
 * " curl -GET -H "msisdn: 0792784894" -H "provider: Vodacom" -H "ussdSessionId: 3" -H "request: 1" -H "requestid: 123" http://localhost:8080/opengov/ussd/cm/stockout "
 * 
 * @author James Lewis
 * @author Sven Siedentopf
 * 
 *
 */
@Controller
@RequestMapping("ussd/cm")
public class CMUssdController extends UssdController<CMUssdRequest, CMUssdResponse>{


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
	 * @return
	 */
	@RequestMapping(value = "{ussdServiceTag}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody
	CMUssdResponse callService(
			@RequestHeader(value = "msisdn") String msisdn,
			@RequestHeader(value = "provider") String provider,
			@RequestHeader(value = "ussdSessionId") String ussdSessionID,
			@RequestHeader(value = "request") String request,
			@RequestHeader(value = "requestid") String requestID,
			@PathVariable String ussdServiceTag) {

		//just for testing
		System.out.println("Invoking USSD service: " + ussdServiceTag);
		System.out.println("Received cell number: " + msisdn);
		System.out.println("Provider: " + provider);
		System.out.println("USSD Session ID: " + ussdSessionID);
		System.out.println("Request String: " + request);
		System.out.println("Request ID: " + requestID);
		
		//construct request object from HTTP headers
		CMUssdRequest ussdRequest = new CMUssdRequest(msisdn, provider, ussdSessionID, request, requestID);

		//delegates to service tier
		CMUssdResponse response = delegateToServices(ussdServiceTag, ussdRequest);

		return response;
	}
	


}
