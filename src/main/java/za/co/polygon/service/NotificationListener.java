package za.co.polygon.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import za.co.polygon.domain.Notification;
import za.co.polygon.repository.MailRepository;

@Component
public class NotificationListener {
	
	@Autowired
	private MailRepository mailRepository;
	
	@Transactional
	@JmsListener(destination="q.notification")
	public void receive(Notification notification) throws AddressException, MessagingException{
		mailRepository.send(notification);
	}

}
