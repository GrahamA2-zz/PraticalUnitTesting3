package uk.co.hydrodev.rules;

import org.junit.rules.*;
import org.junit.runner.*;
import org.junit.runners.model.*;

public class RetryTestRule implements TestRule{

	@Override
	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			
			@Override
			public void evaluate() throws Throwable {
				try {
					base.evaluate();
					return;
				} catch (AssertionError e) {
					base.evaluate();
					return;
				}
				
			}
		};
	}

}
