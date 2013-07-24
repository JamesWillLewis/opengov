package za.org.opengov.stockout.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import za.org.opengov.stockout.models.rest.FacilityResponseModel;
import za.org.opengov.stockout.models.rest.SubmitReportRequestModel;

@Controller
@RequestMapping("stockout/facility")
public class FacilityController {
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public @ResponseBody List<FacilityResponseModel> submitReport(SubmitReportRequestModel submitReportModel) {
		List<FacilityResponseModel> response = new ArrayList<FacilityResponseModel>();
		
		FacilityResponseModel model = new FacilityResponseModel();

		model.setCode("fac-1");
		model.setFacilityName("Facility 1");
		model.setRegion("WC/CT");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-2");
		model.setFacilityName("Facility 2");
		model.setRegion("WC/SW");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-3");
		model.setFacilityName("Facility 3");
		model.setRegion("WC/GB");
		
		response.add(model);
		
		return response;
	}

}
