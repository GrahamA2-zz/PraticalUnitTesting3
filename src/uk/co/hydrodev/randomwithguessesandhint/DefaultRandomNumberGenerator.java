package uk.co.hydrodev.randomwithguessesandhint;

import java.util.*;

public class DefaultRandomNumberGenerator implements RandomNumberGenerator {

	private int maxGuess = 1;
	private int currentGuess = 0;
	private Random random; // = makeRandom();


	public DefaultRandomNumberGenerator(Random random) {
		this.random = random;
	}
	
	public DefaultRandomNumberGenerator() {
		this(new Random());
	}


	@Override
	public void start() {
		random.setSeed(System.currentTimeMillis());
	}

	//18.	Change to Random so I can mock the random number generator
	public Random makeRandom() {
		return new Random();
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
		int value = random.nextInt(maxGuess) + 1;
		this.currentGuess = value <= maxGuess ? value : maxGuess;
	}
	
	
	
}
