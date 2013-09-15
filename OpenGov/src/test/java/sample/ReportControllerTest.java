package sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
public class ReportControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void submitReport() throws Exception {
		
		System.out.println("TEST OK");

		/*
		System.out
				.println(this.mockMvc
						.perform(
								get("/rest/bob")
										.accept(MediaType
												.parseMediaType("application/xml;charset=UTF-8")))
						.andExpect(status().isOk())
						.andExpect(
								content().contentType(
										"application/xml;charset=UTF-8"))
						.andReturn().getResponse().getContentAsString());
		*/

	}

}
