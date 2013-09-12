package za.org.opengov.ussd.controller.cm;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import za.org.opengov.stockout.ussd.model.UssdMenu;
import za.org.opengov.ussd.controller.UssdResponse;

@XmlRootElement(name = "request")
public class CMUssdResponse implements UssdResponse {

	private String displayText;
	
	private String requestID;
	
	public CMUssdResponse() {
	}

	@XmlElement(name = "displaytext")
	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	@XmlElement(name = "requestid")
	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	
	//both requestId and displaytext need to be processed,altered and returned
	//within one method, otherwise will have to run menu logic on both display text
	//and requestID which would double processing time
	public void setDisplayAndRequest(UssdMenu ussdMenuResponse){
		this.displayText = ussdMenuResponse.getMenuResponse();
		this.requestID = ussdMenuResponse.getMenuId();
	}

	
}
