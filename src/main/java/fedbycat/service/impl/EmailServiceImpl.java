package fedbycat.service.impl;

import fedbycat.controller.UserController;
import fedbycat.service.EmailService;
import fedbycat.util.FedByCatParamResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Created by anjin on 4/25/17.
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = Logger.getLogger(EmailServiceImpl.class);

    @Override
    public boolean sendEmail(String emailAddress, int picNumber) {
        String path = FedByCatParamResult.getConfigValue("upload_dir", "system");
        String text = FedByCatParamResult.getConfigValue("text","mail");
        String senderHost = FedByCatParamResult.getConfigValue("host","mail");
        String senderUsername = FedByCatParamResult.getConfigValue("username", "mail");
        String senderPwd = FedByCatParamResult.getConfigValue("pwd", "mail");
        String senderPort = FedByCatParamResult.getConfigValue("port", "mail");

        Properties prop = new Properties();
        prop.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS encryption
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.host", senderHost);
        prop.put("mail.smtp.user", senderUsername);
        prop.put("mail.smtp.password", senderPwd);
        prop.put("mail.smtp.port", senderPort);
        prop.put("mail.smtp.auth", "true");
        log.info("prop");
        log.info(prop);
        Session session = Session.getInstance(prop);
        //only for debugging usage
        session.setDebug(false);

        try {
            Transport ts = session.getTransport();
            ts.connect(senderHost, senderUsername, senderPwd);
            Message message = createEmail(session, text, path, picNumber, emailAddress, senderUsername);
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private MimeMessage createEmail(Session session, String content, String path, int picNumber, String emailAddress, String senderUsername) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        //sender
        message.setFrom(new InternetAddress(senderUsername));
        //receiver
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));

        message.setSubject("Here is your cat today!");

        MimeMultipart multipart = new MimeMultipart("related");

        // first part  (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<H1>" + content + "</H1><img src=\"cid:image\">";
        messageBodyPart.setContent(htmlText, "text/html");

        // add it
        multipart.addBodyPart(messageBodyPart);

        // second part (the image)
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(path + "/" + String.valueOf(picNumber) + ".gif");
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<image>");

        // add it
        multipart.addBodyPart(messageBodyPart);

        // put everything together
        message.setContent(multipart);

//        message.setContent(content, "text/html;charset=UTF-8");
        return message;
    }
}
