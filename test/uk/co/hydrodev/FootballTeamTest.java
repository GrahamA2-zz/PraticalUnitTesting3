package uk.co.hydrodev;


import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
@RunWith(JUnitParamsRunner.class)
public class FootballTeamTest {



	@Before
	public void setUp() throws Exception {
		
	}
	
	public Object[] nbOfGamesWon(){
		return $(0,1,2,3);
	}

	public Object[] illegalNbOfGamesWon(){
		return $(-1,-10);
	}
	
	@Test
	public void canSort(){
		FootballTeam team = new FootballTeam(5);
		assertTrue(team instanceof Comparable);
	}

	
	@Test
	@Parameters(method = "nbOfGamesWon")
	public void testConstructorShouldSetGamesWon(int nbOfGamesWon) {
		FootballTeam team = new FootballTeam(nbOfGamesWon);
		assertThat("Constructor",team.getGamesWon(), equalTo(nbOfGamesWon));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters(method = "illegalNbOfGamesWon")
	public void testIllegalConstructorShouldSetGamesWon(int nbOfGamesWon) {
		FootballTeam team = new FootballTeam(nbOfGamesWon);
		assertThat("Constructor",team.getGamesWon(), equalTo(nbOfGamesWon));
	}
}
