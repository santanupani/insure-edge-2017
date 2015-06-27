package za.co.polygon.listener;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import za.co.polygon.domain.Notification;
import za.co.polygon.repository.NotificationRepository;

@Component
public class NotificationListener {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Transactional
	@JmsListener(destination="q.notification")
	public void receive(Notification notification) throws AddressException, MessagingException{
		notificationRepository.send(notification);
	}

}
