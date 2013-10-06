package za.org.opengov.common.util;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CSVParserTest {
	
	private CSVParser csvParser;

	@Before
	public void setUp() throws Exception {
		
		FileInputStream stream = new FileInputStream(new File(getClass().getClassLoader().getResource("csv_test.csv").getFile()));
		
		csvParser = new CSVParser(stream, "," , "\"");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetElement() {
		Assert.assertEquals("r1c1", csvParser.getElement(0, 0));
		Assert.assertEquals("r1,c2", csvParser.getElement(0, 1));
		Assert.assertEquals("r1c3", csvParser.getElement(0, 2));
		
		Assert.assertEquals("r2c1", csvParser.getElement(1, 0));
		Assert.assertEquals("r2,c2", csvParser.getElement(1, 1));
		Assert.assertEquals("r2c3", csvParser.getElement(1, 2));
		
		Assert.assertEquals("r3c1", csvParser.getElement(2, 0));
		Assert.assertEquals("r3,c2", csvParser.getElement(2, 1));
		Assert.assertEquals("r3c3", csvParser.getElement(2, 2));
	}

}
