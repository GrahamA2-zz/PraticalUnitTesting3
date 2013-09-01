package uk.co.hydrodev.randomwithguesses;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestRandomNumberGenerator {

	RandomNumberGenerator generator = new DefaultRandomNumberGenerator();


	@Before
	public void setUp() throws Exception {
		generator.start();
	}

	@Test
	public void testSetMaxValue() {
		generator.setMaxValue(5);
		assertThat(generator.getMaxValue(), is(5));
	}

	@Test
	public void testNumberIsGreaterThanZero() {
		generator.guessANumber();
		assertThat(generator.getMyNumber(), is(greaterThan(0)));
	}

	@Test
	public void testNumberIsLessThanMax() {	
		generator.setMaxValue(10);
		for (int i = 0 ; i < 1000 ; i++){
			generator.guessANumber();
			assertThat(generator.getMyNumber(), is(lessThanOrEqualTo(10)));
		}
	}
	


}
