package uk.co.hydrodev.randomwithguesses;

public class MyGame {

	private static final int MAX_VALUE = 15;
	private static final int MAX_GUESSES = 20;

	public static void main(String[] args) {
		GameSession session = new GameSession(new DefaultRandomNumberGenerator());
		session.setMaxGuesses(MAX_GUESSES);
		session.setMaxValue(MAX_VALUE);
		System.out.println(session.start());
		int i = 1;
		while ( session.isGameRunning() ) {
			String result = session.makeAGuess(i);
			System.out.println("I'm guessing " + i + ": " + result);
			i++;
		}
		System.out.println("Done");
	}
}
