package uk.co.hydrodev.random;

public interface RandomNumberGenerator {

	void start();
	int getMyNumber();
	void setMaxValue(int maxGuess);
	int getMaxValue();
	void guessANumber();

}
