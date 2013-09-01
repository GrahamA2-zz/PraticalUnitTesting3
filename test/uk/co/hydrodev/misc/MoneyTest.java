package uk.co.hydrodev.misc;


import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(JUnitParamsRunner.class)
public class MoneyTest {

	@Before
	public void setUp() throws Exception {
	}

	private static final Object[] getMoney(){
		return $( $(10,"USD"),
				$(20,"EUR"));
	}


	@Test
	@Parameters(method="getMoney")
	public void testConstuctorShouldSetAmmontAndCurrency(final int ammount, final String currency) {
		final Money money = new Money(ammount, currency);
		assertEquals(ammount, money.getAmmount());
		assertEquals(currency, money.getCurrency());
	}

	@Test(expected = Exception.class)
	public void shouldthrowExceptions(){

	}


	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
