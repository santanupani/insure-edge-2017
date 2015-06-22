package za.co.polygon.repository;

import za.co.polygon.domain.Notification;

public class NotificationRepositoryTest {

    public static void main(String[] args) {

        Notification notification = new Notification();
        notification.setTo("manmay.mohanty@reverside.co.za");
        notification.setFrom("thabothulare68@gmail.com");
        notification.setSubject("Polygon Test Mail");
        notification.setMessage("This is a test notification");

        try {
        	NotificationRepository notificationRepository = new NotificationRepository();
			notificationRepository.send(notification);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}
