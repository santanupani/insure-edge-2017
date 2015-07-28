package za.co.polygon.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import za.co.polygon.domain.Broker;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.repository.MessageRepository;

import com.itextpdf.text.DocumentException;

public class DocumentServiceTest {

    @Test
    public void test() throws DocumentException, IOException {
        DocumentService service = new DocumentService();

        Quotation quotation = new Quotation();
        QuotationRequest quotationRequest = new QuotationRequest();
        quotationRequest.setApplicantName("Thabo");
        quotationRequest.setCompanyName("Reverside Software Solutions");

        quotationRequest.setCreateDate(new Date());
        quotationRequest.setReference("fe0cf511-11cf-4a90-b8c1-661fedd10016");
        
        Product product = new Product();
        product.setName("Cash and Valuables in Transinsit");

        QuotationOption quotationOption = new QuotationOption();
        quotationOption.setCommodity("Gold");
        quotationOption.setLocation("Midrand");
        quotationOption.setExcess("1231");
        quotationOption.setLimit("1000");
        quotationOption.setPremium("12334");
        quotationOption.setPeroid("2 weeks");
        quotationOption.setCover("Static cover");
        quotationOption.setQuotation(quotation);

        QuotationOption quotationOption2 = new QuotationOption();
        quotationOption2.setCommodity("Cash");
        quotationOption2.setLocation("Sandton");
        quotationOption2.setExcess("12000");
        quotationOption2.setLimit("998");
        quotationOption2.setPremium("1999");
        quotationOption2.setPeroid("12 months");
        quotationOption2.setCover("Cash In Transit");
        quotationOption2.setQuotation(quotation);

        
        QuotationOption quotationOption3 = new QuotationOption();
        quotationOption3.setCommodity("Cash");
        quotationOption3.setLocation("Sandton");
        quotationOption3.setExcess("12000");
        quotationOption3.setLimit("998");
        quotationOption3.setPremium("1999");
        quotationOption3.setPeroid("12 months");
        quotationOption3.setCover("Cash In Transit");
        quotationOption3.setQuotation(quotation);
        
        quotationRequest.setProduct(product);

        quotation.setQuotationRequest(quotationRequest);

        List<QuotationOption> quotationOptions = new ArrayList<QuotationOption>();

        quotationOptions.add(quotationOption);
        quotationOptions.add(quotationOption2);
        quotationOptions.add(quotationOption3);

        quotation.setQuotationOptions(quotationOptions);

        byte[] data = service.generateQuotation(quotation);
        FileOutputStream out = new FileOutputStream("target/test.pdf");
        out.write(data);
        out.close();
    }
    
    
    //Test email notification for policy request
    @Test
    public void testSendPolicyRequestNotification(){
    	Broker broker = new Broker();
    	broker.setId(1l);
    	broker.setEmail("lkonaite@gmail.com");
    	
    	QuotationOption selectedOption = new QuotationOption();
    	selectedOption.setId(1l);
    	selectedOption.setLocation("Randburg");
    	selectedOption.setCommodity("Cash and Valuables");
    	selectedOption.setCover("Cash");
    	selectedOption.setExcess("2000.00");
    	
    	List<QuotationOption> quotationOptions = new ArrayList<QuotationOption>();
    	quotationOptions.add(selectedOption);
    	
    	QuotationRequest quotationRequest = new QuotationRequest();
    	quotationRequest.setReference("5a3444b9-849a-473a-b662-65d06a11c117");
    	quotationRequest.setApplicantName("Client Requesting Policy");
    	quotationRequest.setCompanyName("My Company to Run");
    	
    	Quotation quotation = new Quotation();
    	quotation.setId(1l);
    	quotation.setQuotationOptions(quotationOptions);
    	quotation.setQuotationRequest(quotationRequest);
    	
    	PolicyRequest policyRequest = new PolicyRequest();
    	policyRequest.setAccountNumber("123123");
    	policyRequest.setAccountHolder("My Company to Run");
    	policyRequest.setQuotation(quotation); // Set new policy's quotation that requested the quotation
   
    	NotificationService notificationService = new NotificationService();
    	
    	MessageRepository messageRepository = mock(MessageRepository.class);
    	
    	notificationService.setMessageRepository(messageRepository);
    	Assert.assertNotNull(notificationService.getMessageRepository());
    	
    	notificationService.sendNotificationForNewPolicyRequest(quotationRequest, broker);
    	
    	verify(messageRepository,times(1)).publish(notificationService.getNotification(), "q.notification");
    	
    	
    	
    	
    }

}
