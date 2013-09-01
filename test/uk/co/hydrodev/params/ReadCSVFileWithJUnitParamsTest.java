package uk.co.hydrodev.params;

import static org.junit.Assert.*;
import junitparams.*;
import junitparams.mappers.*;

import org.junit.*;
import org.junit.runner.*;


@RunWith(JUnitParamsRunner.class)
public class ReadCSVFileWithJUnitParamsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@FileParameters(value = "classpath:financial.csv", mapper = CsvWithHeaderMapper.class)
	public void testShouldCalculateDiscoun(double value, double discount) {
		assertEquals(discount, DiscountCalculator.calculateDiscount(value,0.0001));
	}

}
