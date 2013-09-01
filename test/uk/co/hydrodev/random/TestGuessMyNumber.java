package uk.co.hydrodev.random;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junitparams.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;

@RunWith(JUnitParamsRunner.class)
public class TestGuessMyNumber {

	@Mock RandomNumberGenerator generator;
	GameSession session;
	private static final int DEFAULT_MAX_VALUE = 10;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(generator.getMaxValue()).thenReturn(DEFAULT_MAX_VALUE);
		session = new GameSession(generator);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGameValidatesItsParameters() {
		new GameSession(null);
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
	
	@Test
	public void testGameSessionTellsMyIAmWrong() {
		session.start();
		when(generator.getMyNumber()).thenReturn(5);
		assertThat(session.makeAGuess(4), is("Wrong!, try again"));
	}
	
	@Test
	@Parameters(method = "vaildNumberOfGuesses")
	public void testToManyGuesses(int maxGuesses ) {
		when(generator.getMyNumber()).thenReturn(5);
		session.setMaxGuesses(maxGuesses);
		session.start();
		for (int guess = 0 ; guess <  ( maxGuesses - 1 ) ; guess++){
			assertThat(session.isGameRunning(), is(true));
			assertThat(session.makeAGuess(4), is("Wrong!, try again"));
		}
		assertThat(session.makeAGuess(4), is("To Many Trys!"));
		assertThat(session.isGameRunning(), is(false));
	}
	
	public Object[] vaildNumberOfGuesses(){
		return $(2,8,9,10);
	}
	
	@Test(expected=IllegalArgumentException.class )
	@Parameters(method = "invalidNumberOfGuesses")
	public void testSetMaxGuessesRejectsInvalidData(int maxGuesses ) {
		session.setMaxGuesses(maxGuesses);
	}
	
	@Test
	public void testSetMaxGuessesIsUsed() {
		session.setMaxGuesses(3);
		session.start();
		assertThat(session.makeAGuess(4), is("Wrong!, try again"));
		assertThat(session.makeAGuess(4), is("Wrong!, try again"));
		assertThat(session.makeAGuess(4), is("To Many Trys!"));
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
