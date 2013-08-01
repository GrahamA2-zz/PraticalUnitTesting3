package uk.co.hydrodev.randomwithguesses;

public interface RandomNumberGenerator {

	void start();
	int getMyNumber();
	void setMaxValue(int maxGuess);
	int getMaxValue();
	void guessANumber();

}
