package com.practicalunittesting.chp05.types;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class MessengerTest {
    private static  final String CLIENT_EMAIL = "some@email.com";
    private static  final String MSG_CONTENT = "Dear John! You are fired.";
    private static  final String TEMPLATE = "Some template";

    @Test
    public void shouldSendEmail(){
        Client client = mock(Client.class);
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = mock(TemplateEngine.class);

        when(client.getEmail()).thenReturn(CLIENT_EMAIL);
        when(templateEngine.prepareMessage(TEMPLATE,client)).thenReturn(MSG_CONTENT);

        Messenger messenger = new Messenger(mailServer,templateEngine);
        messenger.sendMessage(client,TEMPLATE);

        verify(mailServer).send(CLIENT_EMAIL,MSG_CONTENT);
    }
}