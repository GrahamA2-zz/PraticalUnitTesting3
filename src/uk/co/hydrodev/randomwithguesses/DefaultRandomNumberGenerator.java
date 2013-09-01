package uk.co.hydrodev.randomwithguesses;

import java.util.Random;

public class DefaultRandomNumberGenerator implements RandomNumberGenerator {

	private int maxGuess = 1;
	int currentGuess = 0;
	private final Random random = new Random();

	@Override
	public void start() {
		random.setSeed(System.currentTimeMillis());
	}

	@Override
	public int getMyNumber() {
		return currentGuess;
	}

	@Override
	public void setMaxValue(int maxGuess) {
		this.maxGuess = maxGuess;
	}

	@Override
	public int getMaxValue() {
		return maxGuess;
	}

	@Override
	public void guessANumber() {
		this.currentGuess = random.nextInt(maxGuess) + 1;
	}

}
