package za.co.polygon.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import za.co.polygon.domain.Product;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;

import com.itextpdf.text.DocumentException;

public class DocumentServiceTest {
	
	@Test
	public void test() throws DocumentException, IOException{
		DocumentService  service = new DocumentService();
		Quotation quotation = new Quotation();
		QuotationRequest  quotationRequest = new QuotationRequest();
		quotationRequest.setApplicantName("testApplicantName");
		quotationRequest.setCompanyName("testCompanyName");
		quotationRequest.setCreateDate(new Date());
		
		Product product = new Product();
		product.setName("testProductName");
		quotationRequest.setProduct(product);
		
		quotation.setQuotationRequest(quotationRequest);
		
		quotation.setQuotationOptions(new ArrayList<QuotationOption>());
		
		byte[] data = service.generateQuotation(quotation);
		FileOutputStream out = new FileOutputStream("target/test.pdf");
		out.write(data);
		out.close();
	}

}
