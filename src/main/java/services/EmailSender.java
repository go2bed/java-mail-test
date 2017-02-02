package services;

import handlers.MessageHandler;
import entities.MyAuthenticator;
import handlers.PropertiesHandler;

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
        Session session = prepareSession(login, password);
        sendSimpleMessage(session);
        sendMultiMessage(session);
    }

    private static void sendSimpleMessage(Session session)
            throws MessagingException, UnsupportedEncodingException {
        Message msg = new MessageHandler(session).getSimpleMessage();
        Transport.send(msg);
    }


    private static void sendMultiMessage(Session session)
            throws MessagingException, UnsupportedEncodingException {
        Message msg = new MessageHandler(session).getMessageWithAttachment();
        Transport.send(msg);
    }

    private static Session prepareSession(String login, String password){
        Authenticator authenticator = new MyAuthenticator(login, password);
        Properties properties = new PropertiesHandler().getSMTPProperties();
        Session session = Session.getDefaultInstance(properties, authenticator);
        return session;
    }
}
