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
package za.org.opengov.ussd.service;

import java.util.concurrent.Future;

import javax.servlet.http.HttpSession;

import za.org.opengov.ussd.controller.UssdRequest;
import za.org.opengov.ussd.controller.UssdResponse;
import za.org.opengov.ussd.util.KeyValueStore;

/**
 * A generic USSD service, whose sub-classes are expected to provide a USSD
 * service by constucting a response for a given request.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 * 
 * @param <REQ> Request type.
 * @param <RES> Response type. 
 */
public interface UssdService<REQ extends UssdRequest, RES extends UssdResponse> {

	/**
	 * Constructs a response for the given request.
	 * 
	 * A request should contain <b>all</b> the data required to build a response,
	 * other than session data which can be persisted using the {@link KeyValueStore}. 
	 * 
	 * Likewise, the response should contain all the data which the client requires. 
	 * <br>
	 * <b>Note</b>: this method is synchronous. Depending on the requirements, the
	 * implementing class could impose an asynchronous  method call, and the 'response'
	 * would be a {@link Future} instance. 
	 * 
	 * @param request The request which requires a response.
	 * @return The response for the given request. 
	 */
	public RES createUssdResponse(REQ request);

}
