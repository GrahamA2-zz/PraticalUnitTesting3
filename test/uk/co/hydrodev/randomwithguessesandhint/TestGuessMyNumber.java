package uk.co.hydrodev.randomwithguessesandhint;

import static junitparams.JUnitParamsRunner.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.text.*;

import junitparams.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;


@RunWith(JUnitParamsRunner.class)
public class TestGuessMyNumber {

	private static final String HIGHER = "higher";
	private static final String LOWER = "lower";
	@Mock RandomNumberGenerator generator;
	GameSession session;
	private static final int DEFAULT_MAX_VALUE = 10;
	private static final int DEFAULT_MAX_GUESSES = 10;  //2.	We set the number of guesses allowed
	//6.	Reactor by removing the duplicated text from the test
	//10.	Now I make the second change by adding the previous guesses
	//13	To add the hint I start by modifying the error message
	//14	So I roll back the change so i know if it works
	//private static final String WRONG_GUESS =  "Wrong! You have %s guesses left\nYou have tried:%s\ntry a %s number";
	//private static final String WRONG_GUESS =  "Wrong! You have {0, number} guesses left\nYou have tried:{1}\ntry again";
	private static final String WRONG_GUESS =  "Wrong! You have {0, number} guesses left\nYou have tried:{1}\ntry a {2} number";
	//7.	Regression test to show it works

	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(generator.getMaxValue()).thenReturn(DEFAULT_MAX_VALUE);
		session = new GameSession(generator);
		session.setMaxGuesses(DEFAULT_MAX_GUESSES); // 4.	Now we set the default number of guesses to the max
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGameValidatesItsParameters() {
		new GameSession(null);
	}

	@Test
	public void testGameIsNotRunning() {
		assertThat(session.isGameRunning(), is(false));
		assertThat(session.gameOver(), is(true));
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
	
	//Checks that the game fails if the random number generator is not working
	@Test()
	public void testGameSessionStartsAndTheRandomNumberGeneratorFails() {
		doThrow(new RuntimeException()).when(generator).guessANumber();
		assertThat(session.start(), is("Lets play later"));
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
		assertThat(session.gameOver(), is(false));
		assertThat(session.makeAGuess(myNumber), is("Correct!"));
		assertThat(session.gameOver(), is(true));
	}
	
	public Object[] vaildTestData(){
		return $(1,2,3,6,7,8,9,10);
	}
	
	//1.	Now we modify the response to show the number of guesses
	//		Later this was fixed to show a list of guesses 
	@Test
	public void testGameSessionTellsMyIAmWrongLowGuess() {
		session.start();
		when(generator.getMyNumber()).thenReturn(5);
		assertThat(session.makeAGuess(4), is(formatWrongGuess(DEFAULT_MAX_GUESSES - 1, "4", HIGHER)));
	}
	
	@Test
	public void testGameSessionTellsMyIAmWrongHighGuess() {
		session.start();
		when(generator.getMyNumber()).thenReturn(5);
		assertThat(session.makeAGuess(6), is(formatWrongGuess(DEFAULT_MAX_GUESSES - 1, "6", LOWER)));
	}
	
	
	//8.	Ran the manual UAT and found it dosn't work so I add a test to try it out
	//11.	Test error as the string is missing an argument 
	//12.	Later this was fixed to show a list of guesses 
	@Test
	public void testGameGivesTheCorrentResponceToAWrongGuess() {
		session.start();
		when(generator.getMyNumber()).thenReturn(5);	
		assertThat(session.makeAGuess(4), is(formatWrongGuess(DEFAULT_MAX_GUESSES - 1, "4"         , HIGHER)));
		assertThat(session.makeAGuess(3), is(formatWrongGuess(DEFAULT_MAX_GUESSES - 2, "4, 3"      , HIGHER)));
		assertThat(session.makeAGuess(2), is(formatWrongGuess(DEFAULT_MAX_GUESSES - 3, "4, 3, 2"   , HIGHER)));
		assertThat(session.makeAGuess(6), is(formatWrongGuess(DEFAULT_MAX_GUESSES - 4, "4, 3, 2, 6", LOWER)));
	}
	
	//13. I have decided to refactor the test code (see 14) so I go back to a working example and write
	//Used a test
	private static String formatWrongGuess(Object... values){
		String result = MessageFormat.format(WRONG_GUESS, values);
		return result;	
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
			assertThat(session.makeAGuess(MyGuess), is(formatWrongGuess(maxGuesses - guess - 1, madeGuesses, HIGHER )));

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
	
	@Test(expected=IllegalArgumentException.class )
	public void testMakeAGuessRejectsNullData() {
		session.start();
		String guess = null;
		session.makeAGuess(Integer.parseInt(guess));
	}
	
	public Object[] invalidGuess(){
		return $(-1, 0, 11,"Seven",'7');
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
