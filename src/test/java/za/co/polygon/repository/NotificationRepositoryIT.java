package za.co.polygon.repository;

import org.junit.Test;

import za.co.polygon.domain.Notification;

public class NotificationRepositoryIT {

    @Test
    public void testSend() {
        Notification notification = new Notification("thabo.thulare@reverside.co.za", "Polygon Notification Test", "This is a test notification");
        try {
            NotificationRepository notificationRepository = new NotificationRepository();
            notificationRepository.send(notification);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
