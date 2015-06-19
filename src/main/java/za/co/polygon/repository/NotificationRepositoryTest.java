package za.co.polygon.repository;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import za.co.polygon.domain.Notification;

public class NotificationRepositoryTest {

    public static void main(String[] args) {

        NotificationRepository notificationRepository = new NotificationRepository();

        notificationRepository.host = "smtp.gmail.com";
        notificationRepository.port = "587";
      

        Notification notification = new Notification();
        String to = "teethulare@gmail.com";
        String subject = "Test";
        String message = "A test Email message";
        String from = "thabothulare68@gmail.com";
        notification.setTo(to);
        notification.setFrom(from);
        notification.setSubject(subject);
        notification.setMessage(message);

        notificationRepository.send(notification);

    }
}
