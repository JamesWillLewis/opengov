package za.org.opengov.stockout.common.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("stockout/report")
@Deprecated
public class ReportController {
	
	@RequestMapping(value = "submit_report", method = RequestMethod.POST)
	public @ResponseBody String submitReport(SubmitReportRequestModel submitReportModel) {
		String response = "";

		return response;
	}
	
	@RequestMapping(value = "{reportID}", method = RequestMethod.GET)
	public @ResponseBody String getReport(@PathVariable String reportID) {
		String response = String.format("[Default Response for report ID %S ]", reportID);

		
		return response;
	}

}
