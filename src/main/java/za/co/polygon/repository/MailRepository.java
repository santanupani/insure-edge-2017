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

import org.springframework.stereotype.Repository;

import za.co.polygon.domain.Notification;

@Repository
public class MailRepository {

    private static final String username = "thabothulare68@gmail.com"; //Enter your gmail here to test the code
    private static final String password = "Ndivhu@tee1"; //Enter your gmail password here
    private static final String hostName = "smtp.gmail.com";
    private static final int portNumber = 587;

    private Session session;

    public MailRepository() {
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

}
