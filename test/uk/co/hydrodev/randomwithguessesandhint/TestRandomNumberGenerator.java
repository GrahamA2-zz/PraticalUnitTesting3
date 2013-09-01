package uk.co.hydrodev.randomwithguessesandhint;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.*;
import org.mockito.*;

public class TestRandomNumberGenerator {

	private static final int DEFAULT_MAX = 10;
	@Mock private Random mockRandom; 
	private DefaultRandomNumberGenerator generator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		generator = new DefaultRandomNumberGenerator(mockRandom);
		//doReturn( mockRandom ).when( generator ).makeRandom(); 
		generator.setMaxValue(DEFAULT_MAX);
		generator.start();
	}

	@Test
	public void testSetMaxValue() {
		generator.setMaxValue(5);
		assertThat(generator.getMaxValue(), is(5));
	}

	@Test
	public void testNumberIsGreaterThanZero() {
		when(mockRandom.nextInt(DEFAULT_MAX)).thenReturn(0);
		generator.guessANumber();
		verify(mockRandom).nextInt(DEFAULT_MAX);
		assertThat(generator.getMyNumber(), is(greaterThan(0)));
	}
	

	@Test
	public void testNumberIsLessThanMax() {	
		generator.setMaxValue(10);
		when(mockRandom.nextInt(10)).thenReturn(12);
		generator.guessANumber();
		System.out.println((generator.getMyNumber()));
		//Revised version
		assertThat(generator.getMyNumber(), is(lessThanOrEqualTo(10)));
		verify(mockRandom).nextInt(10);
		
	}
	


}
