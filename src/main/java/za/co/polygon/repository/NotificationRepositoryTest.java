package za.co.polygon.repository;

import za.co.polygon.domain.Notification;

public class NotificationRepositoryTest {

    public static void main(String[] args) {

        NotificationRepository notificationRepository = new NotificationRepository();
        Notification notification = new Notification();
        String to = "teethulare@gmail.com";
        String subject = "Test";
        String message = "A test message";

        notification.setTo(to);
        notification.setSubject(subject);
        notification.setMessage(message);

        notificationRepository.send(notification);

    }
}
