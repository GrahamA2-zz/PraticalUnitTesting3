package uk.co.hydrodev.misc;


import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

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
	public void teamsWithMoreMatchesWonShouldBeGreater(){
		FootballTeam team1 = new FootballTeam(2);
		FootballTeam team2 = new FootballTeam(3);
		assertTrue(team2.compareTo(team1) > 0);
	}
		
	@Test
	public void teamsWithMoreMatchesWonShouldBeLess(){
		FootballTeam team1 = new FootballTeam(3);
		FootballTeam team2 = new FootballTeam(2);
		assertTrue(team2.compareTo(team1) < 0);
	}
	
	@Test
	public void teamsWithMoreMatchesWonShouldBeTheSame(){
		FootballTeam team1 = new FootballTeam(3);
		FootballTeam team2 = new FootballTeam(3);
		assertTrue(team2.compareTo(team1) == 0);
	}
	
	@Test
	@Parameters(method = "nbOfGamesWon")
	public void testConstructorShouldSetGamesWon(int nbOfGamesWon) {
		FootballTeam team = new FootballTeam(nbOfGamesWon);
		assertThat("Constructor",team.getGamesWon(), equalTo(nbOfGamesWon));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	@junitparams.Parameters(method = "illegalNbOfGamesWon")
	public void testIllegalConstructorShouldSetGamesWon(int nbOfGamesWon) {
		FootballTeam team = new FootballTeam(nbOfGamesWon);
		assertThat("Constructor",team.getGamesWon(), equalTo(nbOfGamesWon));
	}
	
	
	
	
	
}
