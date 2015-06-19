package za.co.polygon.repository;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import za.co.polygon.domain.Notification;

public class NotificationRepository {

    public void send(final Notification notification) {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(notification.getFrom(), notification.getPassword());
			}
		  });
 		try {
 			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(notification.getFrom()));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(notification.getTo()));
			message.setSubject(notification.getSubject());
			message.setText(notification.getMessage());
 
			Transport.send(message);
 
			System.out.println("Your email was succesfull");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
