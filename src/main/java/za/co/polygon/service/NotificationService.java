package za.co.polygon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.polygon.domain.Broker;
import za.co.polygon.domain.Notification;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.repository.MessageRepository;

@Service
public class NotificationService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public void sendNotificationForNewQuotationRequest(QuotationRequest quotationRequest, Broker broker){
		String to = broker.getEmail();
		String subject = "New Quotation Request";
		String message = String.format(
				"Ref : %s" + "\n" +
	            "Click the link below to view quotation request : " + "\n" +
	            "http://localhost:8080/polygon/broker.html#/quotation-requests/%s", 
	            quotationRequest.getReference(), 
	            quotationRequest.getReference());
		Notification notification = new Notification(to, subject ,  message);
		messageRepository.publish(notification, "q.notification");	              
	}

}