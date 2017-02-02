package handlers;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Andrey_Chadov on 2/1/2017.
 */
public class ConstantsHandler {
    //Attachment location
    public static final String ATTACHMENT = "D:\\att.txt";
    public static final String CHARSET = "UTF-8";
    public static final String SUBJECT = "Subject";
    public static final String CONTENT = "Test content";
    //Constants from property file
    private static final ResourceBundle rb = ResourceBundle.getBundle("java-mail", Locale.getDefault());
    public static final String RECIEVER = rb.getString("mail.receiver");
    public static final String SENDER = rb.getString("mail.sender");
    public static final String SMTP_HOST = rb.getString("mail.smtp.host");
    public static final String SMTP_PORT = rb.getString("mail.smtp.port");

}
