package za.co.polygon.repository;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import za.co.polygon.domain.Notification;

public class NotificationRepository {

	private static final String username = "thabothulare68@gmail.com";
	private static final String password = "Ndivhu@tee1";
	private static final String hostName = "smtp.gmail.com";
	private static final int    portNumber = 587; 
	
    private Session session;
    
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
     
}
