package za.org.opengov.stockout.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import za.org.opengov.stockout.model.request.FacilityResponseModel;
import za.org.opengov.stockout.model.request.SubmitReportRequestModel;

@Controller
@RequestMapping("stockout/facility")
public class FacilityController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public @ResponseBody List<FacilityResponseModel> submitReport(SubmitReportRequestModel submitReportModel) {
		List<FacilityResponseModel> response = new ArrayList<FacilityResponseModel>();
		
		FacilityResponseModel model = new FacilityResponseModel();

		model.setCode("fac-1");
		model.setFacilityName("Facility 1");
		model.setRegion("WC");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-2");
		model.setFacilityName("Facility 2");
		model.setRegion("WC");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-3");
		model.setFacilityName("Facility 3");
		model.setRegion("WC");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-4");
		model.setFacilityName("Facility 4");
		model.setRegion("GT");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-5");
		model.setFacilityName("Facility 5");
		model.setRegion("GT");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-6");
		model.setFacilityName("Facility 6");
		model.setRegion("GT");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-7");
		model.setFacilityName("Facility 7");
		model.setRegion("KZN");
		
		response.add(model);
		
		model = new FacilityResponseModel();
		model.setCode("fac-8");
		model.setFacilityName("Facility 8");
		model.setRegion("KZN");
		
		response.add(model);
		
		return response;
	}

}
