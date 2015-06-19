package za.co.polygon.repository;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.polygon.Service;
import za.co.polygon.domain.Notification;

public class NotificationRepository {

    Session session;
    final String username = "thabothulare68@gmail.com";
    final String password = "Ndivhu@tee1";
    String host;
    String port;
    

    private static final Logger log = LoggerFactory.getLogger(Service.class);

    public NotificationRepository(Session session, String host, String port) {
        this.session = session;
        this.host = host;
        this.port = port;
    }

 
    

    NotificationRepository() {

    }

    public void send(Notification notification) {  
        
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.username", password);
        props.put("mail.smtp.password", username);

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(notification.getFrom()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(notification.getTo()));
            message.setSubject(notification.getSubject());
            message.setText(notification.getMessage());

            Transport.send(message);

           // System.out.println("Your email Service was succesfull ");
            log.info("Your email Service was succesfull");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
