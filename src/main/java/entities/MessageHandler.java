package entities;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static constants.ConstantsHolder.*;

/**
 * @author Andrey_Chadov on 2/1/2017.
 */
public class MessageHandler {
    private Message msg;

    public MessageHandler(Session session) {
        msg = new MimeMessage(session);
    }

    public Message getSimpleMessage() throws MessagingException {
        msg.setFrom(new InternetAddress(SENDER));
        msg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(RECIEVER));
        msg.setSubject(SUBJECT);
        msg.setText(CONTENT);
        return msg;
    }

    public Message getMessageWithAttachment() {


      return msg;
    }
}
