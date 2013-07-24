package uk.co.hydrodev.raceresults;

public interface Client {

	public void receive(Message message);
	public boolean isSubscribedTo(Result result);

}
