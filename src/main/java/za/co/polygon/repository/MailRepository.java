package za.co.polygon.repository;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.Notification;

@Repository
public class MailRepository {

    private  String username;
    private Session session;

    @Autowired
    public MailRepository(@Value("${polygon.mail.hostname}")String host,
    		@Value("${polygon.mail.port}")int port,
    		@Value("${polygon.mail.username}")final String user,
    		@Value("${polygon.mail.password}")final String pass) {
    	
    	this.setUsername(user);
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.username", pass);
        props.put("mail.smtp.password", user);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");        

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        };

        session = Session.getDefaultInstance(props, auth);

    }

    public void send(Notification notification) throws AddressException, MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(getUsername()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(notification.getTo()));
        message.setSubject(notification.getSubject());
        message.setText(notification.getMessage());

       if (notification.getAttachment() != null) {
            Multipart mp = new MimeMultipart();
            BodyPart textPart = new MimeBodyPart();
            textPart.setText(notification.getMessage());
            mp.addBodyPart(textPart);

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setHeader("Content-Type", "application/pdf");

            ByteArrayDataSource rawData = new ByteArrayDataSource(notification.getAttachment(), "application/pdf");
            DataHandler dataHandler = new DataHandler(rawData);
            attachment.setDataHandler(dataHandler);
            attachment.setFileName(notification.getPdfname());
            mp.addBodyPart(attachment);

            message.setContent(mp);
        }
        Transport.send(message);
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
