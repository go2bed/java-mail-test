package entities;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author Andrey_Chadov on 2/1/2017.
 */
public class MyAuthenticator extends Authenticator {
    private String login;
    private String password;

    public MyAuthenticator(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String login = this.login;
        String password = this.password;
        return new PasswordAuthentication(login, password);
    }
}
