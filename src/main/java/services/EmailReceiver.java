package services;

import entities.MessageBean;
import entities.MyAuthenticator;
import handlers.PropertiesHandler;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author Andrey_Chadov on 2/2/2017.
 */
public class EmailReceiver {

    public static void main(String[] args) throws MessagingException, IOException {
        String login = args[0];
        String password = args[1];
        receiveMessage(login, password);
    }
    protected static void receiveMessage(String login, String password) throws MessagingException, IOException {
        Session session = prepareSession(login, password);
        Store store = session.getStore();
        store.connect();
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);


        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        List<String> attachments = new ArrayList<>();

        LinkedList<MessageBean> listMessages = getPart(messages, attachments);


        inbox.setFlags(messages, new Flags(Flags.Flag.SEEN), true);
        inbox.close(false);
        store.close();
    }

    private static LinkedList<MessageBean> getPart(Message[] messages, List<String> attachments) throws MessagingException,  IOException {
        LinkedList<MessageBean> listMessages = new LinkedList<>();
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (Message inMessage : messages) {
            attachments.clear();
            if (inMessage.isMimeType("text/plain")) {
                MessageBean message = new MessageBean(inMessage.getMessageNumber(), MimeUtility.decodeText(inMessage.getSubject()),
                        inMessage.getFrom()[0].toString(), null, f.format(inMessage.getSentDate()), (String) inMessage.getContent(), false, null);
                listMessages.add(message);
            } else if (inMessage.isMimeType("multipart/*")) {
                Multipart mp = (Multipart) inMessage.getContent();
                MessageBean message = null;
                for (int i = 0; i < mp.getCount(); i++) {
                    Part part = mp.getBodyPart(i);
                    if ((part.getFileName() == null || part.getFileName().isEmpty()) && part.isMimeType("text/plain")) {
                        message = new MessageBean(inMessage.getMessageNumber(), inMessage.getSubject(),
                                inMessage.getFrom()[0].toString(), null, f.format(inMessage.getSentDate()),
                                (String) part.getContent(), false, null);
                    } else if (part.getFileName() != null || part.getFileName() != ""){
                        if ((part.getDisposition() != null) && (part.getDisposition().equals(Part.ATTACHMENT))) {
                            attachments.add(saveFile(MimeUtility.decodeText(part.getFileName()), part.getInputStream()));
                            if (message != null) message.setAttachments(attachments);
                        }
                    }
                }
                listMessages.add(message);
            }
        }
        return listMessages;
    }

    private static String saveFile(String filename, InputStream input) {
        String path = "D:\\"+filename + "saved";
        try {
            byte[] attachment = new byte[input.available()];
            input.read(attachment);
            File file = new File(path);
            FileOutputStream out = new FileOutputStream(file);
            out.write(attachment);
            input.close();
            out.close();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    private static Session prepareSession(String login, String password){
        Authenticator authenticator = new MyAuthenticator(login, password);
        Properties properties = new PropertiesHandler().getPOP3Properties();
        Session session = Session.getDefaultInstance(properties, authenticator);
        return session;
    }
}


