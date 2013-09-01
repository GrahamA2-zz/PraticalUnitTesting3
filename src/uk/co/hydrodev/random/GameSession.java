package uk.co.hydrodev.random;

public class GameSession {

	private static final String CORRECT = "Correct!";
	private static final String WRONG = "Wrong!, try again";
	private static final String END_MESSAGE = "To Many Trys!";
	
	private int maxGuesses = 5;
	private final RandomNumberGenerator generator;
	private int guessCount;
	private boolean gameRunning = false;
	
	public GameSession(RandomNumberGenerator generator) {
		if (generator == null)
			throw new IllegalArgumentException("A Random Numner generator is required");
		this.generator = generator;
		generator.start();
	}

	protected int getMyNumber() {
		return generator.getMyNumber();
	}

	/**
	 * Make a guess
	 * @param myNumber Your guess
	 * @return The status of your guess
	 */
	public String makeAGuess(int myNumber) {
		if (!gameRunning)
			throw new IllegalStateException("Game is not running");
		if ( myNumber < 1 || myNumber > generator.getMaxValue() )
			throw new IllegalArgumentException(String.format("%s was not a valid number of guesses", myNumber));
		if( guessCount <  ( maxGuesses - 1 )){
			guessCount++;
			if ( generator.getMyNumber() == myNumber ){
				gameRunning = false;
				return CORRECT;
			} else 
				return WRONG;
		}
		else {
			gameRunning = false;
			return END_MESSAGE;
		}
	}

	/**
	 * Set the maximum number of guesses a player can make
	 * @param maxGuesses
	 */
	public void setMaxGuesses(int maxGuesses) {
		if (gameRunning)
			throw new IllegalStateException("Game is running");
		if ( maxGuesses < 1 )
			throw new IllegalArgumentException(String.format("%s was not a valid number of guesses", maxGuesses));
		this.maxGuesses = maxGuesses;	
	}

	/**
	 * 
	 * @param maxGuess Maximum value for the secret number
	 */
	public void setMaxValue(int maxGuess) {
		if (gameRunning)
			throw new IllegalStateException("Game is running");
		generator.setMaxValue(maxGuess);
	}

	public String start() {
		generator.guessANumber();
		gameRunning = true;
		return "Make a guess";
	}

	public Boolean isGameRunning() {
		return gameRunning;
	}

	public Boolean gameOver() {
		return !gameRunning;
	}



}
