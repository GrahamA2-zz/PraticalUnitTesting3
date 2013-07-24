package uk.co.hydrodev.raceresults;

import java.util.*;

public class RaceResultsService {

	private MessageLogger logger;
	
	private Collection<Client> clients = new HashSet<Client>();

	/**
	 * Add a subscriber to the results service
	 * @param client Results Service subscriber
	 */
	public void addSubscriber(Client client) {
		clients.add(client);
	}

	public void sendMessage(Message message) {
		logger.logMessage(message.getMessageDate(), message.getMessage());
		for (Client client : clients) {
			if ( client.isSubscribedTo(message.getType())){
				client.receive(message);
			} else if (!message.hasType()) {
				client.receive(message);
			}
		}
	}

	public void removeSubscriber(Client client) {
		clients.remove(client);
		
	}

	public void setMessageLogger(MessageLogger logger) {
		this.logger = logger;
	}

}
