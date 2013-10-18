package za.org.opengov.stockout.web.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import za.org.opengov.stockout.entity.Facility;

@Controller
@RequestMapping(value="sows/admin")
public class AdminController {
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String list(Model model) {

		return "admin/Home";
	}

}
