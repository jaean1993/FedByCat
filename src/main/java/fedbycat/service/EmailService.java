package fedbycat.service;

/**
 * Created by anjin on 4/25/17.
 */
public interface EmailService {
    /**
     * send email
     *
     * @title sendEmail
     * @param emailAddress, picNumber, text
     * @return boolean: true if success
     */
    boolean sendEmail(String emailAddress, int picNumber);

}
