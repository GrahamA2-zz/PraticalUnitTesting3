package uk.co.hydrodev;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uk.co.hydrodev.misc.FirstMockitoTest;
import uk.co.hydrodev.misc.FootballTeamTest;
import uk.co.hydrodev.misc.MoneyTest;
import uk.co.hydrodev.misc.PasswordValidator;
import uk.co.hydrodev.raceresults.RaceResultsServiceTest;
import uk.co.hydrodev.userservice.TestUserService;

@RunWith(Suite.class)
@SuiteClasses({ FirstMockitoTest.class
	          , FootballTeamTest.class
	          , MoneyTest.class
	          , PasswordValidator.class
	          , TestUserService.class
	          , RaceResultsServiceTest.class})
public class AllTests {

}
