package za.co.polygon.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import za.co.polygon.domain.Broker;
import za.co.polygon.domain.ClaimRequest;
import za.co.polygon.domain.Notification;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.PolicyRequestType;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.repository.MessageRepository;

@Service
public class NotificationService {

	@Autowired
	private MessageRepository messageRepository;
	private Notification notification;

	@Value("${polygon.application.hostname}")
	private String hostname;

	@Value("${polygon.application.port}")
	private String port;

	public void sendNotificationForNewQuotationRequest(QuotationRequest quotationRequest, Broker broker) {
		String to = broker.getEmail();
		String subject = "New Quotation Request";

		String message = String.format(
				"Ref : %s" + "\n"
						+ "Click the link below to view quotation request : " + "\n"
						+ "http://%s:%s/polygon/broker.html#/quotation-requests/%s",
						quotationRequest.getReference(),
						hostname,
						port,
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
		getMessageRepository().publish(notification, "q.notification");
	}
	public void sendNotificationForRejectPolicyRequest(PolicyRequest policyRequest, String reason) {
		String to = policyRequest.getQuotation().getQuotationRequest().getApplicantEmail();
		String subject = "Policy Request Rejected";
		String message = String.format(
				"Dear %s ," +"\n"
						+ "\n"
						+"Your policy request for quotation Ref : %s has been rejected for the following reason(s)" + "\n"
						+ "\n"
						+"Reason(s):" + "\n"
						+ "%s" + "\n"
						+ "\n"
						+ "Thanks" + "\n"
						+ "Polygon Team",
						policyRequest.getQuotation().getQuotationRequest().getApplicantName(),
						policyRequest.getQuotation().getQuotationRequest().getReference(),
						reason);
		Notification notification = new Notification(to, subject, message);
		getMessageRepository().publish(notification, "q.notification");
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
						+ "http://%s:%s/polygon/client.html#/quotations/%s " + " \n"
						+ "\n"
						+ "Thanks" + "\n"
						+ "Polygon Team",
						quotationRequest.getApplicantName(),
						quotationRequest.getReference(),
						hostname,
						port,
						quotationRequest.getReference());
		String filename = quotationRequest.getApplicantName() + "_quotation.pdf";

		Notification notification = new Notification(to, subject, message, data, filename);
		getMessageRepository().publish(notification, "q.notification");
	}


	public void sendNotificationForNewPolicyRequest(PolicyRequest policyRequest, MultipartFile file, String toUnderwriterEmail,String underWriterName) throws IOException {
		String to = toUnderwriterEmail;
		String subject = "New Policy Request";
		String message = String.format(
				"Dear " +underWriterName+ ",\n\n"
						+ "You have a new Policy Request for Ref. :  %s\nClick the link below to view policy request details: " + "\n"
						+ "http://%s:%s/polygon/underwritter.html#/policy-requests/%s\n\nKind Regards,",
						policyRequest.getQuotation().getQuotationRequest().getReference(),
						hostname,
						port,
						policyRequest.getQuotation().getQuotationRequest().getReference());
		setNotification(new Notification(to, subject, message, file.getBytes(), "bankstatement.pdf"));
		//        		try {
		//        			MailRepository mailRepository = new MailRepository();
		//        			mailRepository.send(notification);
		//        		} catch (Exception e) {
		//        			e.printStackTrace();
		//        		}
		getMessageRepository().publish(getNotification(), "q.notification");     
	}

	public void sendNotificationForNewClaimRequest(ClaimRequest claimRequest, String claimsEmail, String claimsName){

		String to = claimsEmail;
		String subject = "New Claim Request";
		String message = String.format("Dear " + claimsName +",\n \n"
				+ "You have a new Claim Request For Claim No : %s\n click the link below to view claim request" + "\n"
				+ "http://%s:%s/polygon/claim-admin.html#/claim-requests/%s\n\nKind Regards,",
				claimRequest.getClaimNumber(),
				hostname,
				port,
				claimRequest.getClaimNumber());

		setNotification(new Notification(to, subject, message));
		getMessageRepository().publish(getNotification(), "q.notification");  
	}
	
	public void sendNotificationForNewGenericPolicyRequest(PolicyRequestType policyRequestType, String claimsEmail){

		String to = claimsEmail;
		String subject = "New Generic Policy Request";
		String message = String.format("Dear Underwriter,\n \n"
				+ "You have a new POLICY "+policyRequestType.getRequestType().getRequestType()+
				" REQUEST For Policy ["+policyRequestType.getPolicy().getReference()+"]. \nPolicy request Ref No : %s\n\n click the link below to view claim request" + "\n"
				+ "http://%s:%s/polygon/claim-admin.html#/claim-requests/%s\n\nKind Regards,",
				policyRequestType.getReference(),
				hostname,
				port,
				policyRequestType.getReference());

		setNotification(new Notification(to, subject, message));
		getMessageRepository().publish(getNotification(), "q.notification");  
	}

	public MessageRepository getMessageRepository() {
		return messageRepository;
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}
}