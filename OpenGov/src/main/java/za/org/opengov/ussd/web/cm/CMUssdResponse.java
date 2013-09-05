package za.org.opengov.ussd.web.cm;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import za.org.opengov.ussd.web.UssdResponse;

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

	
}
