package uk.co.hydrodev.randomwithguesses;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(JUnitParamsRunner.class)
public class TestGuessMyNumber {

	@Mock RandomNumberGenerator generator;
	GameSession session;
	private static final int DEFAULT_MAX_VALUE = 10;
	private static final int DEFAULT_MAX_GUESSES = 10;  //2.	We set the number of guesses allowed
	//6.	Reactor by removing the duplicated text from the test
	//10.	Now I make the second change by adding the previous guesses
	private static final String WRONG_GUESS =  "Wrong! You have %s guesses left\nYou have tried:%s\ntry again";
	//7.	Regression test to show it works

	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(generator.getMaxValue()).thenReturn(DEFAULT_MAX_VALUE);
		session = new GameSession(generator);
		session.setMaxGuesses(DEFAULT_MAX_GUESSES); // 4.	Now we set the default number of guesses to the max
	}

	@Test
	public void testGameIsNotRunning() {
		assertThat(session.isGameRunning(), is(false));
	}
	
	@Test
	public void testGameSessionStartsGenerator() {
		verify(generator).start();
		assertThat(session.isGameRunning(), is(false));
	}
	
	
	@Test
	public void testGameSessionStarts() {
		assertThat(session.start(), is("Make a guess"));
		verify(generator).guessANumber();
	}
	
	@Test
	@Parameters(method = "vaildTestData")
	public void testGameSessionHasAGuess(int myNumber) {
		when(generator.getMyNumber()).thenReturn(myNumber);
		assertThat(session.getMyNumber(), is(myNumber));
		verify(generator).getMyNumber();
	}
	
	@Test
	@Parameters(method = "vaildTestData")
	public void testGameSessionTellsMyIAmCorrect(int myNumber) {
		session.start();
		when(generator.getMyNumber()).thenReturn(myNumber);
		assertThat(session.isGameRunning(), is(true));
		assertThat(session.makeAGuess(myNumber), is("Correct!"));
		assertThat(session.gameOver(), is(true));
	}
	
	public Object[] vaildTestData(){
		return $(1,2,3,6,7,8,9,10);
	}
	
	//1.	Now we modify the response to show the number of guesses
	//		Later this was fixed to show a list of guesses 
	@Test
	public void testGameSessionTellsMyIAmWrong() {
		session.start();
		when(generator.getMyNumber()).thenReturn(5);
		assertThat(session.makeAGuess(4), is(String.format(WRONG_GUESS, DEFAULT_MAX_GUESSES - 1, "4")));
	}
	
	
	//8.	Ran the manual UAT and found it dosn't work so I add a test to try it out
	//11.	Test error as the string is missing an argument 
	//	Later this was fixed to show a list of guesses 
	@Test
	public void testGameGivesTheCorrentResponceToAWrongGuess() {
		session.start();
		when(generator.getMyNumber()).thenReturn(5);	
		assertThat(session.makeAGuess(4), is(String.format(WRONG_GUESS, DEFAULT_MAX_GUESSES - 1, "4")));
		assertThat(session.makeAGuess(3), is(String.format(WRONG_GUESS, DEFAULT_MAX_GUESSES - 2, "4, 3")));
		assertThat(session.makeAGuess(2), is(String.format(WRONG_GUESS, DEFAULT_MAX_GUESSES - 3, "4, 3, 2")));
		assertThat(session.makeAGuess(6), is(String.format(WRONG_GUESS, DEFAULT_MAX_GUESSES - 4, "4, 3, 2, 6")));
	}
	
	
	@Test
	@Parameters(method = "vaildNumberOfGuesses")
	public void testToManyGuesses(int maxGuesses ) {
		int MyGuess = 4;
		when(generator.getMyNumber()).thenReturn(5);
		session.setMaxGuesses(maxGuesses);
		session.start();
		String madeGuesses = "";
		for (int guess = 0 ; guess <  ( maxGuesses - 1 ) ; guess++){
			assertThat(session.isGameRunning(), is(true));
			//5.	Result as wrong so I need to fix the number of guesses so it changes
			//9.	Had to really think about this to make it work - fixed an out buy one error
			if (madeGuesses.length() == 0) {
				madeGuesses += MyGuess;
			}
			else {
				madeGuesses += ", " + MyGuess;
			}
			assertThat(session.makeAGuess(MyGuess), is(String.format(WRONG_GUESS, (maxGuesses - guess - 1), madeGuesses )));

		}
		assertThat(session.makeAGuess(MyGuess), is("To Many Trys!"));
		assertThat(session.isGameRunning(), is(false));
	}
	
	public Object[] vaildNumberOfGuesses(){
		return $(2,8,9,10,15,30,100);
	}
	
	@Test(expected=IllegalArgumentException.class )
	@Parameters(method = "invalidNumberOfGuesses")
	public void testSetMaxGuessesRejectsInvalidData(int maxGuesses ) {
		session.setMaxGuesses(maxGuesses);
	}
	
	public Object[] invalidNumberOfGuesses(){
		return $(-1,0,"X");
	}
	
	@Test(expected=IllegalArgumentException.class )
	@Parameters(method = "invalidGuess")
	public void testMakeAGuessRejectsInvalidData(int invaldGuess) {
		session.start();
		session.makeAGuess(invaldGuess);
	}
	
	public Object[] invalidGuess(){
		return $(-1, 0, 11);
	}
	
	
	@Test
	public void testSetMaximumNumberToGuess() {
		int maxGuess = 10;
		session.setMaxValue(maxGuess);
		verify(generator).setMaxValue(maxGuess);
	}
	
	@Test
	public void testMaxGuessEdgeCase() {
		int maxGuess = 10;
		session.setMaxValue(maxGuess);
		when(generator.getMyNumber()).thenReturn(maxGuess);
		session.start();
		session.makeAGuess(10);
	}
	
	@Test(expected=IllegalStateException.class )
	public void testThrowsExceptionIfSetMaxIsCalledWhileAGameIsRunning() {
		session.start();
		session.setMaxValue(50);
	}
	
	@Test(expected=IllegalStateException.class )
	public void testThrowsExceptionIfSetMaxGuessIsCalledWhileAGameIsRunning() {
		session.start();
		session.setMaxGuesses(100);
	}
	
	@Test(expected=IllegalStateException.class )
	public void testThrowsExceptionIfGameIsNotYetRunning() {
		session.makeAGuess(4);
	}
}
