package constants;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Andrey_Chadov on 2/1/2017.
 */
public class ConstantsHolder {
    //Constants from property file
    private static final ResourceBundle rb = ResourceBundle.getBundle("java-mail", Locale.getDefault());
    public static String RECIEVER = rb.getString("mail.receiver");
    public static String SENDER = rb.getString("mail.sender");
    public static String SMTP_HOST = rb.getString("mail.smtp.host");
    public static String SMTP_PORT = rb.getString("mail.smtp.port");
    //Attachment location
    public static String ATTACHMENT = "D:\\att.txt";


    public static String CHARSET = "UTF-8";
    public static String SUBJECT = "Subject";
    public static String CONTENT = "Test content from Andrey Chadov";

}
