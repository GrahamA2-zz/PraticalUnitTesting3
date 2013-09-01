package uk.co.hydrodev.randomwithguessesandhint;

import java.util.*;

public class FactoryHelperRandomNumberGenerator implements RandomNumberGenerator {
	
	static class RandomNumberGeneratorHelper{ 
		Random makeRandom( ){ 
			return new Random(); 
		} 
	}	
	
	private int maxGuess = 1;
	private int currentGuess = 0;
	private Random random = makeRandom();
	private RandomNumberGeneratorHelper helper; 
	
	FactoryHelperRandomNumberGenerator(){ 
        this( new RandomNumberGeneratorHelper()); 
    } 

	FactoryHelperRandomNumberGenerator(RandomNumberGeneratorHelper helper){
		this.helper = helper;
	}	
	
	@Override
	public void start() {
		random.setSeed(System.currentTimeMillis());
	}

	
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
		this.currentGuess = random.nextInt(maxGuess) + 1;
	}

	
}
