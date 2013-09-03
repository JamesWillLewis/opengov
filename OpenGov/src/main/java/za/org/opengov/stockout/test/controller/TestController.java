package za.org.opengov.stockout.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import za.org.opengov.stockout.test.model.TestEntity;
import za.org.opengov.stockout.test.service.TestService;


@Controller
@RequestMapping("stockout/test")
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getTestList()
	{
		String response = "";
		for(TestEntity entity: testService.getAllTests()){
			response += entity.getName() + "\n";
		}
		
		return response;
	}
	
}
