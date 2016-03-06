package za.co.polygon.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRException;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.ClaimRequest;
import za.co.polygon.domain.Notification;
import za.co.polygon.domain.Policy;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.PolicyRequestType;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.repository.MessageRepository;

@Service
public class NotificationService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private DocumentService documentService;
	
	private Notification notification;

	@Value("${polygon.application.hostname}")
	private String hostname;

	@Value("${polygon.application.port}")
	private String port;

	public void sendNotificationForNewQuotationRequest(QuotationRequest quotationRequest, Broker broker) {
		String to = broker.getEmail();
//                String cc = "obertvdw@gmail.com";
                String cc = "binod.sethi@gmail.com";
		String subject = "New Quotation Request";

		String message = String.format(
				"Ref : %s" + "<br>"
						+ "Click the link below to view quotation request : " + "<br>"
						+ "http://%s:%s/polygon/broker.html#/quotation-requests/%s" + "<br>"
                                                + "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>",    
						quotationRequest.getReference(),
						hostname,
						port,
						quotationRequest.getReference());
		Notification notification = new Notification(to, cc, subject, message);
		messageRepository.publish(notification, "q.notification");
	}
        
        public void sendNotificationForOldQuotationRequest(QuotationRequest quotationRequest, Broker broker) {
		String to = broker.getEmail();
//                String cc = "obertvdw@gmail.com";
                String cc = "binod.sethi@gmail.com";
		String subject = "New Quotation Request";

		String message = String.format(
				"Ref : %s" + "<br>"
						+ "Click the link below to view Updated quotation request : " + "<br>"
						+ "http://%s:%s/polygon/broker.html#/requotation-requests/%s" + "<br>"
                                                + "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>",    
						quotationRequest.getReference(),
						hostname,
						port,
						quotationRequest.getReference());
		Notification notification = new Notification(to, cc, subject, message);
		messageRepository.publish(notification, "q.notification");
	}

	public void sendNotificationForRejectQuotationRequest(QuotationRequest quotationRequest, String reason) {
		String to = quotationRequest.getApplicantEmail();
//                String cc = "obertvdw@gmail.com";
              String cc = "binod.sethi@gmail.com";
		String subject = "Quotation Request Rejected";
		String message = String.format(
				"Dear %s ," +"<br>"
						+ "<br>"
						+"Your request for quotation Ref : %s has been rejected for the following reason(s)" + "<br>"
						+ "<br>"
						+"Reason(s):" + "<br>"
						+ "%s" + "<br>"
						+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"
                                                + "<br>",
						quotationRequest.getApplicantName(),
						quotationRequest.getReference(),
						reason);
		Notification notification = new Notification(to, cc, subject, message);
		getMessageRepository().publish(notification, "q.notification");
	}
	public void sendNotificationForRejectPolicyRequest(PolicyRequest policyRequest, String reason) {
		String to = policyRequest.getQuotation().getQuotationRequest().getApplicantEmail();
//                String cc = "obertvdw@gmail.com";
              String cc = "binod.sethi@gmail.com";
		String subject = "Policy Request Rejected";
		String message = String.format(
				"Dear %s ," +"<br>"
						+ "<br>"
						+"Your policy request for quotation Ref : %s has been rejected for the following reason(s)" + "<br>"
						+ "<br>"
						+"Reason(s):" + "<br>"
						+ "%s" + "<br>"
						+ "<br>"
						+ "<br>"+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>",
						policyRequest.getQuotation().getQuotationRequest().getApplicantName(),
						policyRequest.getQuotation().getQuotationRequest().getReference(),
						reason);
		Notification notification = new Notification(to, cc, subject, message);
		getMessageRepository().publish(notification, "q.notification");
	}

	public void sendNotificationForAcceptQuotationRequest(QuotationRequest quotationRequest,byte[] data){
		String to = quotationRequest.getApplicantEmail();
//                String cc = "obertvdw@gmail.com";
              String cc = "binod.sethi@gmail.com";
		String subject = "Quotation Request Accepted";
		String message = String.format(
				"Dear %s ," +"<br>"
						+ "<br>"
						+"Your request for quotation Ref : %s has been accepted" + "<br>"
						+ "<br>"
						+"Please find the attachment to view your quotation" + "<br>"
						+ "<br>"
						+ "Please click the link below to apply for a policy" + " <br>"
						+ "http://%s:%s/polygon/client.html#/quotations/%s " + " <br>"
						+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>",   
						quotationRequest.getApplicantName(),
						quotationRequest.getReference(),
						hostname,
						port,
						quotationRequest.getReference());
		String filename = quotationRequest.getApplicantName() + "_quotation.pdf";

		Notification notification = new Notification(to, cc, subject, message, data, filename);
		getMessageRepository().publish(notification, "q.notification");
	}


	public void sendNotificationForNewPolicyRequest(PolicyRequest policyRequest, MultipartFile file, String toUnderwriterEmail,String underWriterName) throws IOException {
		String to = toUnderwriterEmail;
//                String cc = "obertvdw@gmail.com";
                String cc = "binod.sethi@gmail.com";
		String subject = "New Policy Request";
		String message = String.format(
				"Dear " +underWriterName+ ",<br><br>"
						+ "You have a new Policy Request for Ref. :  %s\nClick the link below to view policy request details: " + "<br>"
						+ "http://%s:%s/polygon/underwritter.html#/policy-requests/%s<br>"
                                                + "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>",
						policyRequest.getQuotation().getQuotationRequest().getReference(),
						hostname,
						port,
						policyRequest.getQuotation().getQuotationRequest().getReference());
		setNotification(new Notification(to, cc, subject, message, file.getBytes(), "bankstatement.pdf"));
		//        		try {
		//        			MailRepository mailRepository = new MailRepository();
		//        			mailRepository.send(notification);
		//        		} catch (Exception e) {
		//        			e.printStackTrace();
		//        		}
		getMessageRepository().publish(getNotification(), "q.notification");     
	}

	
	public void sendNotificationForRequestPolicyApproval(Policy policy, String managerEmail){

		String subject = "Policy Approval Request";
//                String cc = "obertvdw@gmail.com";
                String cc = "binod.sethi@gmail.com";
		String message = String.format("Dear Gerard,<br> <br>"
				+ "You have a new policy approval request." + "<br>"
				+"<br>Policy Approval request Ref No : %s\n\n click the link below to view approval request" + "<br>"
				+ "http://%s:%s/polygon/manager.html#/policy/%s/approval"
                                + "Trust you find the above in order." + "<br>" 
                                + "Kind Regards," + "<br>"
                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                + "<br>" 
                                + "<br>"
                                + "<img src=\"cid:image\">" + "<br><br>"
                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                + "<br>",
				policy.getReference(),
				hostname,
				port,
				policy.getReference());

		setNotification(new Notification(managerEmail, cc, subject, message));
		getMessageRepository().publish(getNotification(), "q.notification");  
	}
	
	public void sendNotificationForApprovalToUnderwritter(Policy policy,String underwritterEmail) {
            
//		String cc = "obertvdw@gmail.com";
                String cc = "binod.sethi@gmail.com";
		String subject = "Policy "+policy.getReference()+" Approved.";

		String message = String.format("Dear Underwriter,<br> <br>"
				+ "This is to notify you that policy request for temporary policy ref: %s" + 
				" has been approved and the policy is currently active.<br><br> click the link below to view approved policy" + "<br>"
				+ "http://%s:%s/polygon/underwritter.html#/policy/%s/admin<br>"
                                + "Trust you find the above in order." + "<br>" 
                                + "Kind Regards," + "<br>"
                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                + "<br>" 
                                + "<br>"
                                + "<img src=\"cid:image\">" + "<br><br>"
                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                + "<br>",
				policy.getReference(),
				hostname,
				port,
				policy.getReference());
		Notification notification = new Notification(underwritterEmail, cc, subject, message);
		messageRepository.publish(notification, "q.notification");
	}
	
	public void sendNotificationForApprovalToBroker(Policy policy, String brokerEmail) throws IOException, JRException {
            
//		String cc = "obertvdw@gmail.com";
                String cc = "binod.sethi@gmail.com";
		String subject = "Policy Schedule for Client "+policy.getClient().getClientName();
		String message = String.format("Dear Broker,<br> <br>"
						+ "This is to notify you that policy request for client: %s"+
						" has been created. Attached please find the Policy Schedule for the client."
						+ "Please note that the client can view the Policy Details by clicking on the link below" + "<br>"
						+ "http://%s:%s/polygon/client-admin.html#/clients/%s<br>"
                                                + "<br>Kind Regards,"+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>",
						policy.getClient().getClientName(),
						hostname,
						port,
						policy.getClient().getId());
		setNotification(new Notification(brokerEmail, cc, subject, message, documentService.policyScheduleReportPDF(policy), 
				"Policy Schedule for - "+policy.getClient().getClientName()+"-"+policy.getReference()+".pdf"));
		getMessageRepository().publish(getNotification(), "q.notification");     
	}


    public void sendNotificationForDeclineClaimRequest(ClaimRequest claimRequest, String reason) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
//        String cc = "obertvdw@gmail.com";
        String cc = "binod.sethi@gmail.com";
        String subject = "Claim Request Declined";
        String message = String.format(
                "Dear %s ," + "<br>"
                + "<br>"
                + "Your request for Claim No : %s has been rejected for the following reason(s)" + "<br>"
                + "<br>"
                + "Reason(s):" + "<br>"
                + "%s" + "<br>"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br><br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>",
                claimRequest.getPolicy().getClient().getClientName(),
                claimRequest.getClaimNumber(),
                reason);
        Notification notification = new Notification(to, cc, subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }


    public void sendNotificationForRequestApprovalForClaimRequest(ClaimRequest claimRequest) {
        String to = "polygon.broker@gmail.com";
//        String cc = "obertvdw@gmail.com";
        String cc = "binod.sethi@gmail.com"; 
        String subject = "Claim Request Provisionally Approval by Susan Atto";
        String message = String.format(
                "Dear Hentie Snyder ," + "<br>"
                + "<br>"
                + "Claim request for Claim No : %s has been provisionally approved by Susan" + "<br>"
                + "<br>"
                + "Please click the link below to view the claim approved by Claim's Department" + " <br>"
                + "<br>"       
                + "http://%s:%s/polygon/broker.html#/claimRequests/%s " + " <br>"
                + "<br>"
                + "Susan Atto"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>",
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, cc, subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }
    public void sendNotificationForRequestingPendingDocuments(ClaimRequest claimRequest) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
        
//        String cc = "obertvdw@gmail.com";
        String cc = "binod.sethi@gmail.com";
        String subject = "Claim Documents Pending";
        String message = String.format(
                "Dear %s ," + "<br>"
                + "<br>"
                + "Please upload claim supporting documents for Claim Number : %s , so that your claim can be processed" + "<br>"
                + "<br>"
                + "Please click the link below to finish your claim" + " <br>"
                + "http://%s:%s/polygon/client-admin.html#/claim-requests/%s/claim " + " <br>"
                + "<br>"
                + "Susan Atto"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>",
                claimRequest.getPolicy().getClient().getClientName(),
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, cc, subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }

    public void sendNotificationForApproveClaimRequest(ClaimRequest claimRequest) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
//        String cc = "obertvdw@gmail.com";
        String cc = "binod.sethi@gmail.com";
        String subject = "Claim Request Approved by Hentie Snyder";
        String message = String.format(
                "Dear Susan Atto ," + "<br>"
                + "<br>"
                + "Claim request for Claim No : %s has been approved by Hentie Snyder" + "<br>"
                + "<br>"
                + "Please click the link below to apply for a policy" + " <br>"
                + "http://%s:%s/polygon/broker.html#/claimRequests/%s " + " <br>"
                + "<br>"
                + "Hentie Synder"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>",
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, cc, subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }
    
    public void sendNotificationForAcceptedClaimRequest(ClaimRequest claimRequest) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
//        String cc = "obertvdw@gmail.com";
        String cc = "binod.sethi@gmail.com";
        String subject = "Claim Request Approved by Gerard";
        String message = String.format(
                "Dear Susan Atto ," + "<br>"
               + "<br>"
                + "Claim request for Claim No : %s has been approved by Hentie Snyder" + "<br>"
                + "<br>"
                + "Please click the link below to apply for a policy" + " <br>"
                + "http://%s:%s/polygon/broker.html#/claimRequests/%s " + " <br>"
                + "<br>"
                + "Hentie Synder"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>",
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, cc, subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }

    

    public void sendNotificationForNewClaimRequest(ClaimRequest claimRequest, String claimsEmail, String claimsName) {

        String to = claimsEmail;
//        String cc = "obertvdw@gmail.com";
        String cc = "binod.sethi@gmail.com";
        String subject = "New Claim Request";
        String message = String.format("Dear " + claimsName + ",<br> <br>"
                + "You have a new Claim Request For Claim No : %s\n click the link below to view claim request" + "<br>"
                + "http://%s:%s/polygon/claim-admin.html#/claim-requests/%s<br>"
                + "<br>"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>",
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());

        setNotification(new Notification(to, cc, subject, message));
        getMessageRepository().publish(getNotification(), "q.notification");
    }

    public void sendNotificationForNewGenericPolicyRequest(PolicyRequestType policyRequestType, String claimsEmail) {

        String to = claimsEmail;
//        String cc = "obertvdw@gmail.com";
        String cc = "binod.sethi@gmail.com";
        String subject = "New Cancellation Policy Request";
        String message = String.format("Dear Underwriter,<br> <br>"
                + "You have a new POLICY " + policyRequestType.getRequestType().getRequestType()
                + " REQUEST For Policy [" + policyRequestType.getPolicy().getReference() + "]. <br> Policy request type Ref No : %s<br><br> click the link below to view claim request for Cancellation" + "<br>"
                + "http://%s:%s/polygon/claim-admin.html#/claim-requests/%s<br>"
                + "<br>"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Polygon Underwriting Agency (Pty) Ltd" + "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>",
                policyRequestType.getReference(),
                hostname,
                port,
                policyRequestType.getReference());

        setNotification(new Notification(to, cc, subject, message));
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
