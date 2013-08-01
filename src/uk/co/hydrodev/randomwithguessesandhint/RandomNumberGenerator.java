package uk.co.hydrodev.randomwithguessesandhint;


interface RandomNumberGenerator {

	void start();
	int getMyNumber();
	void setMaxValue(int maxGuess);
	int getMaxValue();
	void guessANumber();

}
