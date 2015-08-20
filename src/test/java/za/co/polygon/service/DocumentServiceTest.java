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
        product.setId(1l);

        QuotationOption quotationOption = new QuotationOption();
        quotationOption.setCommodity("Gold");
        quotationOption.setLocation("Midrand");
        quotationOption.setExcess("1231");
        quotationOption.setLimit("1000");
        quotationOption.setPremium("12334");
        quotationOption.setCover("Static cover");
        quotationOption.setQuotation(quotation);

        QuotationOption quotationOption2 = new QuotationOption();
        quotationOption2.setCommodity("Cash");
        quotationOption2.setLocation("Sandton");
        quotationOption2.setExcess("12000");
        quotationOption2.setLimit("998");
        quotationOption2.setPremium("1999");
        quotationOption2.setCover("Cash In Transit");
        quotationOption2.setQuotation(quotation);

        
        QuotationOption quotationOption3 = new QuotationOption();
        quotationOption3.setCommodity("Cash");
        quotationOption3.setLocation("Sandton");
        quotationOption3.setExcess("12000");
        quotationOption3.setLimit("998");
        quotationOption3.setPremium("1999");
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

}
