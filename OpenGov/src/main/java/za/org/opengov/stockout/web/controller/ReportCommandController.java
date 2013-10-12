package za.org.opengov.stockout.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import za.org.opengov.stockout.service.FacilityService;

@Controller
public class ReportCommandController {


	
	
	private static final Logger LOG = LoggerFactory.getLogger(ReportCommandController.class);
	
	@Autowired
	private FacilityService facilityService;
	
	@RequestMapping(value="/reportstockouts",method=RequestMethod.GET)
	public String getReportPage(Model model){
		String[] medicineCategories = new String[4];
		medicineCategories[0]= "arvs";
		medicineCategories[1]= "tb";
		medicineCategories[2]= "arvs";
		medicineCategories[3]= "arvs";
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		   
		Date date = new Date();
		
		List<String> provinces = facilityService.listAllProvinces();
		
		model.addAttribute("date",dateFormat.format(date));
		model.addAttribute("medicineCategories", medicineCategories);
		model.addAttribute("provinces",provinces);
		
		return("Report_Page");
	}
	
	@RequestMapping(value = "/getfacility", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<String> getFacilty(@RequestParam(value="province") String province){			
		return(facilityService.listAllDistrictsForProvince(province));		
	}
	
}
