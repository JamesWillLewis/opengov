package za.org.opengov.ussd.web;


public class UssdRequest {
	
	private String msisdn;
	private String provider;
	private String ussdSessionId;
	private String request;
	private String requestid;
	
	
	/**
	 * 
	 * @param msisdn
	 * @param provider
	 * @param ussdSessionId
	 * @param request
	 * @param requestid
	 */
	public UssdRequest(String msisdn, String provider, String ussdSessionId,
			String request, String requestid) {
		super();
		this.msisdn = msisdn;
		this.provider = provider;
		this.ussdSessionId = ussdSessionId;
		this.request = request;
		this.requestid = requestid;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getUssdSessionId() {
		return ussdSessionId;
	}
	public void setUssdSessionId(String ussdSessionId) {
		this.ussdSessionId = ussdSessionId;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	
	

}
