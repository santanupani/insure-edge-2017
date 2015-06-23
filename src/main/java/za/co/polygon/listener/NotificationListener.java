package za.co.polygon.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import za.co.polygon.domain.Notification;

@Component
public class NotificationListener {
	
	@Transactional
	@JmsListener(destination="q.test")
	public void receive(Notification message){
		System.out.println(message.getMessage() + "Received");
	}

}
