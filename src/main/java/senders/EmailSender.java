package senders;

import entities.MessageHandler;
import entities.MyAuthenticator;
import entities.PropertiesHandler;

import javax.mail.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author Andrey_Chadov on 2/1/2017.
 */
public class EmailSender {

    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        String login = args[0];
        String password = args[1];
        sendSimpleMessage(login, password);
    }

    public static void sendSimpleMessage(String login, String password)
            throws MessagingException, UnsupportedEncodingException {
        Authenticator authenticator = new MyAuthenticator(login, password);
        Properties properties = new PropertiesHandler().getSMTPProperties();
        Session session = Session.getDefaultInstance(properties, authenticator);
        Message msg = new MessageHandler(session).getSimpleMessage();
        Transport.send(msg);
    }

}