package handlers;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.io.UnsupportedEncodingException;

import static handlers.ConstantsHandler.*;

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

    public Message getMessageWithAttachment() throws MessagingException, UnsupportedEncodingException {
        //First off all creating simple message
        msg = getSimpleMessage();

        //Then add multipart content
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(CONTENT, "text/html; charset=" + CHARSET + "");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        //Add attachment file
        DataSource dataSource = new FileDataSource(ATTACHMENT);
        attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
        attachmentBodyPart.setFileName(MimeUtility.encodeText(dataSource.getName()));
        multipart.addBodyPart(attachmentBodyPart);
        //Set multipart with file in email body
        msg.setContent(multipart);
        return msg;
    }
}
