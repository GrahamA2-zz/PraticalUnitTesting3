package uk.co.hydrodev.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	
	@Test
	public void testStubbing() {
		assertFalse("new test double should return false as boolean",myFerrari.needsFuel());
		when(myFerrari.needsFuel()).thenReturn(true);
		assertTrue("after instructed test double should return what we want",myFerrari.needsFuel());

	}
	

}
