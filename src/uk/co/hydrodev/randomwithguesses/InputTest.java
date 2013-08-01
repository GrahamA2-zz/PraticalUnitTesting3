package uk.co.hydrodev.randomwithguesses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputTest {

	public static void main(String[] args) {
		try {
			System.err.println("Revision 1");
			int maxNumber =  10;
			int maxGuesses = 10;
			System.out.print(String.format("Lets play a guessing game: I'm thinking of a number between 1 and %s,\nyou have %s tries\n",maxNumber,maxGuesses));
			GameSession session = new GameSession(new DefaultRandomNumberGenerator());
			session.setMaxGuesses(maxGuesses);
			session.setMaxValue(maxNumber);
			System.out.println(session.start());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (session.isGameRunning()) {
				String str = br.readLine();
				System.out.println("You just entered: " + str);
				try {
					int guess = Integer.parseInt(str);
					String result = session.makeAGuess(guess);
					System.out.println(result);
				} catch (Exception e) {
					System.out.println(String.format("%s was not a valid guess" ,str));
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}