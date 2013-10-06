package za.org.opengov.stockout.web.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import za.org.opengov.stockout.web.domain.stockoutResult;



@Controller
public class SiteController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);
	
	/*public stockoutResult[] getList(Model model){
		ArrayList<stockoutResult> stockoutNames = new ArrayList<stockoutResult>();
		
	    return result;
	}*/
	
	@RequestMapping(value = "/stockoutHome",method = RequestMethod.GET)
	public String getHomePage(Model model){
		LOG.debug("Stockout web Application Front-End");
		String name = "Test Application";
		System.out.println();
		
		stockoutResult[] result = new stockoutResult[3];
		for (int i=0;i<3;i++){
			result[i] = new stockoutResult();
			result[i].setProvince("Gauteng");
		}
		model.addAttribute("stockoutResult", result);
			
		return("Stockout Home");
	}
}
