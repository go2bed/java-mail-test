package entities;

import java.util.Properties;

import static constants.ConstantsHolder.*;


/**
 * Created by Andrey_Chadov on 2/1/2017.
 */
public class PropertiesHandler {

    public Properties getSMTPProperties(){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", SMTP_HOST);
        properties.put("mail.smtp.host", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.mime.charset", CHARSET);
        return properties;
    }
}
