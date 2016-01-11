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
						+"Please find the attachment to view your quotation" + "\n"
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

	
	public void sendNotificationForRequestPolicyApproval(Policy policy, String managerEmail){

		String subject = "Policy Approval Request";
		String message = String.format("Dear Gerard,\n \n"
				+ "You have a new policy approval request."
				+"\nPolicy Approval request Ref No : %s\n\n click the link below to view approval request" + "\n"
				+ "http://%s:%s/polygon/manager.html#/policy/%s/approval\n\nKind Regards,",
				policy.getReference(),
				hostname,
				port,
				policy.getReference());

		setNotification(new Notification(managerEmail, subject, message));
		getMessageRepository().publish(getNotification(), "q.notification");  
	}
	
	public void sendNotificationForApprovalToUnderwritter(Policy policy,String underwritterEmail) {
		
		String subject = "Policy "+policy.getReference()+" Approved.";

		String message = String.format("Dear Underwriter,\n \n"
				+ "This is to notify you that policy request for temporary policy ref: %s"+
				" has been approved and the policy is currently active.\n\n click the link below to view approved policy" + "\n"
				+ "http://%s:%s/polygon/underwritter.html#/policy/%s/admin\n\nKind Regards,",
				policy.getReference(),
				hostname,
				port,
				policy.getReference());
		Notification notification = new Notification(underwritterEmail, subject, message);
		messageRepository.publish(notification, "q.notification");
	}
	
	public void sendNotificationForApprovalToBroker(Policy policy, String brokerEmail) throws IOException, JRException {
		
		String subject = "Policy Schedule for Client "+policy.getClient().getClientName();
		String message = String.format("Dear Broker,\n \n"
						+ "This is to notify you that policy request for client: %s"+
						" has been created. Attached please find the Policy Schedule for the client."
						+ "Please note that the client can view the Policy Details by clicking on the link below" + "\n"
						+ "http://%s:%s/polygon/client.html#/clients/%s\n\nKind Regards,",
						policy.getClient().getClientName(),
						hostname,
						port,
						policy.getClient().getId());
		setNotification(new Notification(brokerEmail, subject, message, documentService.policyScheduleReportPDF(policy), 
				"Policy Schedule for - "+policy.getClient().getClientName()+"-"+policy.getReference()+".pdf"));
		getMessageRepository().publish(getNotification(), "q.notification");     
	}


    public void sendNotificationForDeclineClaimRequest(ClaimRequest claimRequest, String reason) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
        String subject = "Claim Request Declined";
        String message = String.format(
                "Dear %s ," + "\n"
                + "\n"
                + "Your request for Claim No : %s has been rejected for the following reason(s)" + "\n"
                + "\n"
                + "Reason(s):" + "\n"
                + "%s" + "\n"
                + "\n"
                + "Thanks" + "\n"
                + "Polygon Team",
                claimRequest.getPolicy().getClient().getClientName(),
                claimRequest.getClaimNumber(),
                reason);
        Notification notification = new Notification(to, subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }

    public void sendNotificationForProvisionallyApproveClaimRequest(ClaimRequest claimRequest) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
        String subject = "Claim Request Provisionally Approved by Susan Atto";
        String message = String.format(
                "Dear Hentie Snyder ," + "\n"
                + "\n"
                + "Claim request for Claim No : %s has been provisionally approved by Susan" + "\n"
                + "\n"
                + "Please click the link below to apply for a policy" + " \n"
                + "http://%s:%s/polygon/broker.html#/claimRequests/%s " + " \n"
                + "\n"
                + "Thanks" + "\n"
                + "\n"
                + "Susan Atto",
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }

    public void sendNotificationForApproveClaimRequest(ClaimRequest claimRequest) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
        String subject = "Claim Request Approved by Hentie Snyder";
        String message = String.format(
                "Dear Susan Atto ," + "\n"
                + "\n"
                + "Claim request for Claim No : %s has been approved by Hentie Snyder" + "\n"
                + "\n"
                + "Please click the link below to apply for a policy" + " \n"
                + "http://%s:%s/polygon/broker.html#/claimRequests/%s " + " \n"
                        + "\n"
                + "Thanks" + "\n"
                        + "\n"
                + "Hentie Snyder",
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }

    

    public void sendNotificationForNewClaimRequest(ClaimRequest claimRequest, String claimsEmail, String claimsName) {

        String to = claimsEmail;
        String subject = "New Claim Request";
        String message = String.format("Dear " + claimsName + ",\n \n"
                + "You have a new Claim Request For Claim No : %s\n click the link below to view claim request" + "\n"
                + "http://%s:%s/polygon/claim-admin.html#/claim-requests/%s\n\nKind Regards,",
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());

        setNotification(new Notification(to, subject, message));
        getMessageRepository().publish(getNotification(), "q.notification");
    }

    public void sendNotificationForNewGenericPolicyRequest(PolicyRequestType policyRequestType, String claimsEmail) {

        String to = claimsEmail;
        String subject = "New Generic Policy Request";
        String message = String.format("Dear Underwriter,\n \n"
                + "You have a new POLICY " + policyRequestType.getRequestType().getRequestType()
                + " REQUEST For Policy [" + policyRequestType.getPolicy().getReference() + "]. \nPolicy request Ref No : %s\n\n click the link below to view claim request" + "\n"
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
