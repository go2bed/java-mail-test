package handlers;

import java.util.Properties;

import static handlers.ConstantsHandler.*;


/**
 * @author Andrey_Chadov on 2/1/2017.
 */
public class PropertiesHandler {

    private Properties properties = System.getProperties();

    public Properties getSMTPProperties() {
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.mime.charset", CHARSET);
        return properties;
    }

    public Properties getPOP3Properties() {
        properties.put("mail.host", "pop.gmail.com");
        properties.put("mail.debug", "false");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        return properties;
    }
}
