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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.service.FacilityService;

@Controller
@RequestMapping(value="sows")
public class ContactsQueryController {

private static final Logger LOG = LoggerFactory.getLogger(ReportCommandController.class);
	
@Autowired
private FacilityService facilityService;


/**Load up list containing names of facilities and their assosciated contact information based on parameter values
 * (values entered by users when choosing to search for a facility)*/

	@RequestMapping(value="/loadcontacts",method=RequestMethod.GET)
	public String getReportPage(@RequestParam (value = "facilityName", required = false, defaultValue = "") String facilityName,
			@RequestParam (value = "province", required = false, defaultValue = "all") String province
			,Model model){
		List<Facility> facilities = new ArrayList<Facility>();
		List<String> provinces = new ArrayList<String>();
		
		if (facilityName.length()>0){
			facilities.add(facilityService.getClosestMatch(facilityName));
		}
		else if (province.equals("all")){		
			facilities = facilityService.getAll();
		}  
		else{
			facilities = facilityService.listAllFacilitiesForProvince(province);
		}
		
		provinces = facilityService.listAllProvinces();
		model.addAttribute("provinces",provinces);	
		model.addAttribute("facilities", facilities);
		
		return("Contacts_Page");
	}
	
}
