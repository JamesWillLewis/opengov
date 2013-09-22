package za.org.opengov.common.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringMatcherTest {

	private StringMatcher stringMatcher;

	private StringMatchableSample sample1 = new StringMatchableSample("Hhello");
	private StringMatchableSample sample2 = new StringMatchableSample("Hheelloo");
	private StringMatchableSample sample3 = new StringMatchableSample("Hhhellloo");
	private StringMatchableSample sample4 = new StringMatchableSample("Hhhelllooo");
	private StringMatchableSample sample5 = new StringMatchableSample("Zhhelllooo");

	@Before
	public void setUp() throws Exception {

		stringMatcher = new StringMatcher();
		stringMatcher.addStringMatchable(sample1);
		stringMatcher.addStringMatchable(sample2);
		stringMatcher.addStringMatchable(sample3);
		stringMatcher.addStringMatchable(sample4);
		stringMatcher.addStringMatchable(sample5);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetClosestMatch() {
		assertEquals("Get Closest Match", sample1,
				stringMatcher.getClosestMatch("hello"));
	}

	@Test
	public void testGetClosestMatches() {
		StringMatchableSample sample = new StringMatchableSample("hello");
		List<StringMatchable> results = stringMatcher.getClosestMatches(sample,
				5);

		assertEquals("Get Closest Match 1", sample1, results.get(0));
		assertEquals("Get Closest Match 2", sample2, results.get(1));
		assertEquals("Get Closest Match 3", sample3, results.get(2));
		assertEquals("Get Closest Match 4", sample4, results.get(3));
		assertEquals("Get Closest Match 5", sample5, results.get(4));
	}

	private class StringMatchableSample implements StringMatchable {

		private String string;

		public StringMatchableSample(String string) {
			this.string = string;
		}

		@Override
		public String getStringToMatch() {
			return string;
		}

		@Override
		public String toString() {
			return string;
		}

	}

}
