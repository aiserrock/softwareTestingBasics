package com.practicalunittesting.chp05.raceresults;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RaceResultsServiceTest {

    RaceResultsService raceResults = new RaceResultsService();
    Message message = mock(Message.class);
    Client clientA = mock(Client.class,"clientA");
    Client clientB  = mock(Client.class,"clientB");
    @Test
    public void messageShouldBeSentToAllSubscribedClients(){
        raceResults.addSubscriber(clientA);
        raceResults.addSubscriber(clientB);
        raceResults.send(message);
        verify(clientA).receive(message );
        verify(clientB).receive(message );
    }

    @Test
    public void notSubscribedCilentSouldNotReceiveMessage(){
        raceResults.send(message);
        verify(clientA,never()).receive(message);
    }

    @Test
    public void shouldSendOnlyOneMessageToMultiSubscriber(){
         raceResults.addSubscriber(clientA);
         raceResults.addSubscriber(clientA);
         raceResults.send(message);
         verify(clientA,only()).receive(message);


    }

    public void unsubscribedClientShouldNotReceiveMessages(){
        raceResults.addSubscriber(clientA);
        raceResults.removeSubscriber(clientA);
        raceResults.send(message);
        verify(clientA,never()).receive(message);
    }

}