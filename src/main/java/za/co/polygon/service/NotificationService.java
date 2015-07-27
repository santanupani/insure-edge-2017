package za.co.polygon.service;

import java.io.File;
import java.io.IOException;
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

    public void sendNotificationForNewQuotationRequest(QuotationRequest quotationRequest, Broker broker) {
        String to = broker.getEmail();
        String subject = "New Quotation Request";
        String message = String.format(
                "Ref : %s" + "\n"
                + "Click the link below to view quotation request : " + "\n"
                + "http://localhost:8080/polygon/broker.html#/quotation-requests/%s",
                quotationRequest.getReference(),
                quotationRequest.getReference());
        Notification notification = new Notification(to, subject, message);
        messageRepository.publish(notification, "q.notification");
    }

    public void sendNotificationForRejectQuotationRequest(QuotationRequest quotationRequest, String reason) {
        String to = quotationRequest.getApplicantEmail();
        String subject = "Quotation Request Rejected";
        String message = String.format(
                 "Dear %s ," +"\n"
                + "\n"
                +"Your request for quotation Ref : %s has been rejected for the following reason(s)" + "\n"
                + "\n"
                +"Reason(s):" + "\n"
                + "%s" + "\n"
                + "\n"
                + "Thanks" + "\n"
                + "Polygon Team",
                quotationRequest.getApplicantName(),
                quotationRequest.getReference(),
                reason);
        Notification notification = new Notification(to, subject, message);
        messageRepository.publish(notification, "q.notification");
    }
    
    public void sendNotificationForAcceptQuotationRequest(QuotationRequest quotationRequest,byte[] data){
        String to = quotationRequest.getApplicantEmail();
        String subject = "Quotation Request Accepted";
        String message = String.format(
                 "Dear %s ," +"\n"
                + "\n"
                +"Your request for quotation Ref : %s has been accepted" + "\n"
                + "\n"
                +"Please find the attachement t view your quotation" + "\n"
                + "\n"
                + "Please click the link below to apply for a policy" + " \n"
                + "http://localhost:8080/polygon/client.html#/quotations/%s " + " \n"
                + "\n"
                + "Thanks" + "\n"
                + "Polygon Team",
                quotationRequest.getApplicantName(),
                quotationRequest.getReference(),
                quotationRequest.getReference());
        String filename = quotationRequest.getApplicantName() + "_quotation.pdf";
        
        Notification notification = new Notification(to, subject, message, data, filename);
        messageRepository.publish(notification, "q.notification");
    }
    
    

}
