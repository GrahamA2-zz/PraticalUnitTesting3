package uk.co.hydrodev.raceresults;

public interface Message {

	public Result getType();
	public boolean hasType();
	public String getMessageDate();
	public String getMessage();

}
