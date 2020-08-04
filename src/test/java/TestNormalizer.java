import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import spring.transformer.CapLeadingNormalizer;
import spring.transformer.INormalizer;

import static org.testng.Assert.assertEquals;

public class TestNormalizer {

	INormalizer normalizer = new CapLeadingNormalizer();

	@DataProvider
	Object[][] data() {
		return new Object[][] {
				{"this is a test", "This Is A Test"},
				{"This IS a test", "This Is A Test"},
				{"this  is  a test", "This Is A Test"}
		};
	}

	@Test(dataProvider = "data")
	public void testNormalization(String input, String expected) {
		assertEquals(normalizer.transform(input), expected);
	}
}
