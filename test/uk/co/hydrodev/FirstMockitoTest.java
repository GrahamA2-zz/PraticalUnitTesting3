package uk.co.hydrodev;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class FirstMockitoTest {
	
	private Car myFerrari = mock(Car.class);

	@Test
	public void test() {
		assertTrue(myFerrari instanceof Car);
	}
	
	@Test
	public void testDefaultBehaviourofTestDouble() {
		assertFalse("new test double should return false as boolean", myFerrari.needsFuel());
		assertEquals("new test double should return 0.0 as double", 0.0,  myFerrari.getEngineTemperature(),0.1);
	}
	
	
	

}
