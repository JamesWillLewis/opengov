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

import za.org.opengov.ussd.controller.UssdRequest;



public class CMUssdRequest implements UssdRequest {
	
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
	public CMUssdRequest(String msisdn, String provider, String ussdSessionId,
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
