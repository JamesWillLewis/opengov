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
