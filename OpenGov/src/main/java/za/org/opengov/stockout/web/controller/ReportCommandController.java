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
