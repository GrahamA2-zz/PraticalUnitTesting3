package uk.co.hydrodev.rules;

import org.junit.*;

public class RetryRuleTest {

	
	@Rule
	public RetryTestRule retryTestRule = new RetryTestRule();
	
	static int executionNumber = 0;
	
	@Test
	public void test() {
		executionNumber++;
		System.out.println("exection: " + executionNumber);
		Assert.fail("Still failing: " + executionNumber);
	}

}
