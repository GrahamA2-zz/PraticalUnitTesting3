package uk.co.hydrodev;

import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.SuiteClasses;

import uk.co.hydrodev.misc.*;
import uk.co.hydrodev.raceresults.*;
import uk.co.hydrodev.userservice.*;

@RunWith(Suite.class)
@SuiteClasses({ FirstMockitoTest.class
	          , FootballTeamTest.class
	          , MoneyTest.class
	          , PasswordValidator.class
	          , TestUserService.class
	          , RaceResultsServiceTest.class
	          , uk.co.hydrodev.random.AllTests.class
	          , uk.co.hydrodev.randomwithguesses.AllTests.class
	          , uk.co.hydrodev.randomwithguessesandhint.AllTests.class})
public class AllTests {

}
