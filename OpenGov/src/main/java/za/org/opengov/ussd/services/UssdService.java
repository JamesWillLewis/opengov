package za.org.opengov.ussd.services;

import za.org.opengov.ussd.channelmobileapi.UssdRequest;
import za.org.opengov.ussd.channelmobileapi.UssdResponse;

public interface UssdService {

	public UssdResponse createUssdResponse(UssdRequest request);

}
