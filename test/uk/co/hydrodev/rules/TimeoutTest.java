package uk.co.hydrodev.rules;

import org.junit.*;
import org.junit.rules.*;

public class TimeoutTest {

	@Rule public Timeout globalTimeout = new Timeout(20);
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testWillFail() throws InterruptedException {
		Thread.sleep(1000);
	}
	
	@Test
	public void testWillPass() throws InterruptedException {
		Thread.sleep(10);
	}

}
