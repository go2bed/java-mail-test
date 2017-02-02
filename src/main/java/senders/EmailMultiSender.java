package senders;

import entities.MessageHandler;
import entities.MyAuthenticator;
import entities.PropertiesHandler;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.util.Properties;

/**
 * @author Andrey_Chadov on 2/1/2017.
 */
public class EmailMultiSender {

    public static void main(String[] args) throws MessagingException {
        String login = args[0];
        String password = args[1];
        sendMultiMessage(login, password);
    }

    private static void sendMultiMessage(String login, String password) throws MessagingException {
        Authenticator authenticator = new MyAuthenticator(login, password);
        Properties properties = new PropertiesHandler().getSMTPProperties();
        Session session = Session.getDefaultInstance(properties, authenticator);
        Message msg = new MessageHandler(session).getSimpleMessage();

    }
}
