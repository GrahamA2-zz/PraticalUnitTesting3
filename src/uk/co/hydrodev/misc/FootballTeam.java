package uk.co.hydrodev.misc;

public class FootballTeam implements Comparable<FootballTeam>{

	int gamesWon = 0;
	
	public FootballTeam(int i) {
		if ( i < 0 )
			throw new IllegalArgumentException();
		this.gamesWon = i;
	}

	public Integer getGamesWon() {
		return gamesWon;
	}

	@Override
	public int compareTo(FootballTeam o) {
		// TODO Auto-generated method stub
		return this.gamesWon - o.gamesWon;
	}

}
