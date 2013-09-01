package uk.co.hydrodev.raceresults;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class RaceResultsServiceTest {

	private static final String MESSAGE_DATA = null;
	private static final String MESSAGE_TEXT = null;
	@Mock private RaceResultsService raceReasults;
	@Mock private Client clientA;
	@Mock private Client clientB;
	@Mock private Message message1;
	@Mock private MessageLogger logger;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		raceReasults = new RaceResultsService();
		raceReasults.setMessageLogger(logger);
	}

	@Test
	public void testSubscribedClientShouldReciveMessage() {
		raceReasults.addSubscriber(clientA);
		raceReasults.sendMessage(message1);
		verify(clientA).receive(message1);
	}
	
	@Test
	public void testSubscribedClientShouldAllSubscribedClients() {
		raceReasults.addSubscriber(clientA);
		raceReasults.addSubscriber(clientB);
		raceReasults.sendMessage(message1);
		verify(clientA).receive(message1);
		verify(clientB).receive(message1);
	}

	@Test
	public void testUnSubscribedClientShouldNotNotified() {
		raceReasults.addSubscriber(clientA);
		raceReasults.sendMessage(message1);
		verify(clientA).receive(message1);
		verify(clientB,never()).receive(message1);
	}
	
	@Test
	public void testClientShouldGetOneMessage() {
		raceReasults.addSubscriber(clientA);
		raceReasults.addSubscriber(clientA);
		raceReasults.sendMessage(message1);
		verify(clientA).receive(message1);
	}
	
	
	@Test
	public void testUnSubscribedClientShouldNotNotifiedAfterItsRemoved() {
		raceReasults.addSubscriber(clientA);
		raceReasults.removeSubscriber(clientA);
		raceReasults.sendMessage(message1);
		verify(clientA,never()).receive(message1);
	}
	
	
	
	
	@Test
	public void testClientShouldGetMessagesTheyAreSubscribedTo() {
		raceReasults.addSubscriber(clientA);
		raceReasults.addSubscriber(clientB);
		Result f1Race = new FIRace();
		//Set-up the response conditions
		when(message1.getType()).thenReturn(f1Race);
		when(message1.hasType()).thenReturn(true);
		when(clientA.isSubscribedTo(f1Race)).thenReturn(true);
		
		raceReasults.sendMessage(message1);
		
		verify(clientA).isSubscribedTo(f1Race);
		verify(clientA).receive(message1);
		verify(clientB,never()).receive(message1);

	}
	
	@Test
	public void testMessagesAreLogged() {
		raceReasults.addSubscriber(clientA);
		raceReasults.addSubscriber(clientB);
		Result f1Race = new FIRace();
		//Set-up the response conditions
		when(message1.getType()).thenReturn(f1Race);
		when(message1.hasType()).thenReturn(true);
		
		when(message1.getMessageDate()).thenReturn(MESSAGE_DATA);
		when(message1.getMessage()).thenReturn(MESSAGE_TEXT);
		
		raceReasults.sendMessage(message1);
		verify(logger).logMessage(MESSAGE_DATA,MESSAGE_TEXT);
	}

}
