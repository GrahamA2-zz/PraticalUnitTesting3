package uk.co.hydrodev.randomwithguessesandhint;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.MessageFormat;

import org.junit.Before;
import org.junit.Test;

public class MessageSpike {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testMyCode() {	
		assertThat(formatWrongGuess(3,"4, 5, 6"), is("Wrong! You have 3 guesses left\nYou have tried:4, 5, 6\ntry again"));
	}
	
	private static String formatWrongGuess(Object... values){
		String WRONG_GUESS =  "Wrong! You have {0, number} guesses left\nYou have tried:{1}\ntry again";	
		String result = MessageFormat.format(WRONG_GUESS, values);
		System.out.println(result);
		return result;	
	}
}
