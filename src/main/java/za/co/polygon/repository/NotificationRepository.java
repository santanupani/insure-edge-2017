package za.co.polygon.repository;

import java.util.Properties;

import javax.jms.JMSException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.Notification;

@Repository
public class NotificationRepository {

    private static final String username = "binod.polygon@gmail.com"; //Enter your gmail here to test the code
    private static final String password = "polygonmidrand"; //Enter your gmail password here
    private static final String hostName = "smtp.gmail.com";
    private static final int portNumber = 587;

    private Session session;

    @Autowired
    private JmsTemplate jmsTemplate;

    public NotificationRepository() {
        Properties props = new Properties();
        props.put("mail.smtp.host", hostName);
        props.put("mail.smtp.port", portNumber);
        props.put("mail.smtp.username", password);
        props.put("mail.smtp.password", username);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        session = Session.getDefaultInstance(props, auth);

    }

    public void send(Notification notification) throws AddressException, MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(notification.getTo()));
        message.setSubject(notification.getSubject());
        message.setText(notification.getMessage());
        Transport.send(message);
    }

    public void publish(final Notification notification){

        jmsTemplate.send("q.notification", new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(javax.jms.Session session) throws JMSException {
                return session.createObjectMessage(notification);
            }
        });

    }

}
