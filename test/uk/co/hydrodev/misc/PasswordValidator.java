package uk.co.hydrodev.misc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PasswordValidator {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPasswoprdLengthIsValid() {
		Password pass = new Password("AbcDef");
		assertThat(pass.isValid(), is(true));
	}
	
	@Test
	public void testLowerCasePasswordIsInvalid() {
		Password pass = new Password("abcdef");
		assertThat(pass.isValid(), is(false));
	}
	
	@Test
	public void testUpperCasePasswordIsInvalid() {
		Password pass = new Password("ABCDEF");
		assertThat(pass.isValid(), is(false));
	}

	@Test
	public void testShortLengthIsInValid() {
		Password pass = new Password("ab");
		assertThat(pass.isValid(), is(false));
	}
}
