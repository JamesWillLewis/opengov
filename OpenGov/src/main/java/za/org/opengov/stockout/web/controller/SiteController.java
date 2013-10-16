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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.map.util.JSONWrappedObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.StockoutService;
import za.org.opengov.stockout.service.domain.LocationHeirarchy;
import za.org.opengov.stockout.service.medical.MedicineClassService;
import za.org.opengov.stockout.service.medical.MedicineService;
import za.org.opengov.stockout.web.domain.graphData;
import za.org.opengov.stockout.web.domain.stockoutResult;
import za.org.opengov.ussd.controller.cm.CMUssdResponse;


@Transactional
@Controller
public class SiteController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);
	
	/*public stockoutResult[] getList(Model model){
		ArrayList<stockoutResult> stockoutNames = new ArrayList<stockoutResult>();
		
	    return result;
	}*/
	
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private MedicineClassService medicineClassService;
	
	@Autowired
	private StockoutService stockoutService;
	
	@Autowired
	private MedicineService medicineService;
	
	@RequestMapping(value = "/stockouthome",method = RequestMethod.GET)
	public String getHomePage(Model model,
			@RequestParam(value="province") String province,
			@RequestParam(value="district") String district,
			@RequestParam(value="town") String town,
			@RequestParam(value="medicineCat") String medicineCat){
		
		LOG.debug("Stockout web Application Front-End");
		List<String> provinces = facilityService.listAllProvinces();
		List<Long> provinceStockouts = new ArrayList<Long>();
		if (province.equals("all")){
			for (String prov : provinces){
				
				provinceStockouts.add(facilityService.totalStockoutsForProvince(prov));
				
			}
		}
		
		List<MedicineClass> medicines = medicineClassService.getAll();
		model.addAttribute("locations",provinceStockouts);
		model.addAttribute("provinces", provinces);
		model.addAttribute("medicines", medicines);
		
		
		return("Stockout Home");
	}
	
	@RequestMapping(value = "/getdistricts", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<String> getDistricts(@RequestParam(value="province") String province){			
		return(facilityService.listAllDistrictsForProvince(province));		
	}
	
	@RequestMapping(value = "/gettowns", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<String> getTowns(@RequestParam(value="district") String district){			
		return(facilityService.listAllTownsForDistrict(district));		
	}
	
	@RequestMapping(value="/getgraphdata", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  graphData getGraphData(@RequestParam(value="province") String province,
			@RequestParam(value="district") String district,
			@RequestParam(value="town") String town,
			@RequestParam(value="medicineCat") String medicineCat){
		
		graphData graphs = new graphData();
		List<String> locations = new ArrayList<String>();
		List<Long> data = new ArrayList<Long>();
		List<Long> medData = new ArrayList<Long>();
		List<String> names = new ArrayList<String>();
		
		if (province.equals("all")){
			locations = facilityService.listAllProvinces();
			
				for (String loc : locations){
				
				data.add(facilityService.totalStockoutsForProvince("Western Cape"));
				
			}
				
		}

		else if (district.equals("all")){
			locations = facilityService.listAllDistrictsForProvince(province);
			
			for (String loc : locations){
				
				data.add(facilityService.totalStockoutsForDistrict(loc));
				
			}
		} 
		
		else if (town.equals("all")) {
			locations = facilityService.listAllTownsForDistrict(district);
			
			for (String loc : locations){
				
				data.add(facilityService.totalStockoutsForTown(loc));
				
			}
			
		} else
		{
			List<Facility> facilities= facilityService.listAllFacilitiesForTown(town);
			
			for (Facility fac : facilities){
				locations.add(fac.getLocalName());
				data.add((long) fac.getStockouts().size());
			}
			
		}
		
		List<MedicineClass> medicineClasses = medicineClassService.getAll();
		
		if (medicineCat.equals("all")){
			
			for (MedicineClass medClass : medicineClasses){
				names.add(medClass.getUid());
				medData.add((long)stockoutService.getStockoutsForMedicineClass(medClass).size());
			}
			
		} 
		else {
			Set<Medicine> medicines = medicineClassService.get(medicineCat).getMedicines();
			for (Medicine med : medicines){
				names.add(med.getName());
				medData.add((long)stockoutService.getStockoutsForMedicine(med).size());
			}
		}
		
		
		graphs.setLocations(locations);
		graphs.setLocationStockouts(data);
		graphs.setMedicines(names);
		graphs.setMedicineStockouts(medData);

		return(graphs);
		
	}
	
}
