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
package za.org.opengov.ussd.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import za.org.opengov.ussd.service.UssdService;

public abstract class UssdController <REQ extends UssdRequest, RES extends UssdResponse> {
	
	private final String USSD_PROTOCOL_PREFIX;
	
	public UssdController(final String USSD_PROTOCOL_PREFIX) {
		this.USSD_PROTOCOL_PREFIX = USSD_PROTOCOL_PREFIX;
	}

	/**
	 * All services which implement the UssdService interface will be
	 * automatically injected into this map. The key for each element will match
	 * the service bean-name. i.e. "@Service("stockout")"
	 */
	@Autowired
	private Map<String, UssdService<REQ, RES>> ussdServices;

	protected RES delegateToServices(String serviceTag,
			REQ ussdRequest) {
		return ussdServices.get(USSD_PROTOCOL_PREFIX + '.' + serviceTag).createUssdResponse(ussdRequest);
	}

}
