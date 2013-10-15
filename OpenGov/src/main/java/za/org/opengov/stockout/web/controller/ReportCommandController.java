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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.medical.MedicineClassService;

@Controller
public class ReportCommandController {

	private static final Logger LOG = LoggerFactory.getLogger(ReportCommandController.class);
	
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private MedicineClassService medicineClassService;
	
	@RequestMapping(value="/reportstockouts",method=RequestMethod.GET)
	public String getReportPage(Model model){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		   
		Date date = new Date();
		
		List<String> provinces = facilityService.listAllProvinces();
		List<MedicineClass> medicineClasses = medicineClassService.getMedicineClassesEagerFetch();
		model.addAttribute("medicineCategories", medicineClasses);
		model.addAttribute("date",dateFormat.format(date));
		model.addAttribute("provinces",provinces);
		
		return("Report_Page");
	}
	
	@RequestMapping(value = "/getfacility", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<String> getFacilty(@RequestParam(value="province") String province){
		List<Facility> allFacilities = facilityService.listAllFacilitiesForProvince(province);
		List<String> facilityNames = new ArrayList<String>();
		for(Facility fac : allFacilities){
			facilityNames.add(fac.getLocalName() + " " + fac.getFacilityType().getReadable());		
		}
		
		return(facilityNames);		
	}
	
	@RequestMapping(value = "/getmedicines", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<String> getMedicines(@RequestParam(value="medicineClassIndex") int medicineClassIndex){
		
		List<String> productDescription = new ArrayList<String>();
		List<MedicineClass> medicineClasses = medicineClassService.getMedicineClassesEagerFetch();
		Set<Medicine> medicineNames = medicineClasses.get(medicineClassIndex).getMedicines();
		
		for(Medicine med : medicineNames){
			for (Product prod: med.getProducts()){
				
				productDescription.add(prod.getName() + " " + prod.getDescription());
				
			}
		}
		
		return(productDescription);		
	}
	
}
