package handlers;

import java.util.Properties;

import static constants.ConstantsHolder.*;


/**
 * @author Andrey_Chadov on 2/1/2017.
 */
public class PropertiesHandler {

  private Properties properties = System.getProperties();

    public Properties getSMTPProperties(){
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.mime.charset", CHARSET);
        return properties;
    }


}
