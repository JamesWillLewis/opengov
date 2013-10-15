package za.org.opengov.stockout.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import za.org.opengov.stockout.entity.TestEntity;
import za.org.opengov.stockout.service.TestService;



@Controller
@RequestMapping("stockout/api/test")
@Deprecated
public class TestControllerRest {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public @ResponseBody List<TestEntity> getTestList()
	{
		return testService.getAllTests);
	}
	
}
