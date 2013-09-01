package uk.co.hydrodev.randomwithguesses;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGuessMyNumber.class, TestRandomNumberGenerator.class })
public class AllTests {

}
