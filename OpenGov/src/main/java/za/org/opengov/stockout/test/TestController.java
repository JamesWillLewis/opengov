package za.org.opengov.stockout.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("stockout/test")
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public @ResponseBody List<TestEntity> getTestList()
	{
		return testService.getAllTests();
	}
	
}
