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
package za.org.opengov.stockout.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.service.FacilityService;

/**controls all requests for the Contacts Page**/
@Controller
public class ContactsQueryController {

private static final Logger LOG = LoggerFactory.getLogger(ReportCommandController.class);
	
@Autowired
private FacilityService facilityService;

	/** Return a model object containing a list of all facilities and their respective contact details */
	@RequestMapping(value="/loadcontacts",method=RequestMethod.GET)
	public String getReportPage(Model model){
		
		List<Facility> facilities = facilityService.getAll();
		model.addAttribute("facilities", facilities);
		
		return("Contacts_Page");
	}
}
