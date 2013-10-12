package za.org.opengov.stockout.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactsQueryController {

private static final Logger LOG = LoggerFactory.getLogger(ReportCommandController.class);
	
	@RequestMapping(value="/loadcontacts",method=RequestMethod.GET)
	
	public String getReportPage(Model model){
		
		return("Contacts_Page");
	}
}
