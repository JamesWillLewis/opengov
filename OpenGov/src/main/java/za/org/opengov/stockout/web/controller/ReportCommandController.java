package za.org.opengov.stockout.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportCommandController {

	private static final Logger LOG = LoggerFactory.getLogger(ReportCommandController.class);
	
	@RequestMapping(value="/reportStockouts",method=RequestMethod.GET)
	public String getReportPage(Model model){
		String[] medicineCategories = new String[4];
		medicineCategories[0]= "arvs";
		medicineCategories[1]= "tb";
		medicineCategories[2]= "arvs";
		medicineCategories[3]= "arvs";
		
		model.addAttribute("medicineCategories", medicineCategories);
		
		return("Report_Page");
	}
	
	
}
