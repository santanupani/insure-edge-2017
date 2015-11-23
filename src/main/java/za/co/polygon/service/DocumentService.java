/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.polygon.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.FileResolver;
import za.co.polygon.domain.Answer;
import za.co.polygon.domain.Policy;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;

@Service
public class DocumentService{

	private String quotationWording;
	NumberFormat nfm = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);


	FileResolver fileResolver = new FileResolver() {

		public File resolveFile(String fileName) {
			URI uri;
			try {
				uri = new URI(this.getClass().getResource(fileName).getPath());
				return new File(uri.getPath());
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return null;
			}
		}
	};


	public byte[] generateQuotationPDF(Quotation quotation) throws JRException{
		
		
		
		Map<String,Object> reportData = new HashMap<String,Object>();
		
		for(int i=0;i<quotation.getQuotationOptions().size();i++){
			
			quotation.getQuotationOptions().get(i).setLimit(nfm.format(Double.parseDouble(quotation.getQuotationOptions().get(i).getLimit())).replace(",", ".").replace("$", ""));
			quotation.getQuotationOptions().get(i).setPavements(nfm.format(Double.parseDouble(quotation.getQuotationOptions().get(i).getPavements())).replace(",", ".").replace("$", ""));
			quotation.getQuotationOptions().get(i).setStaticLimit(nfm.format(Double.parseDouble(quotation.getQuotationOptions().get(i).getStaticLimit())).replace(",", ".").replace("$", ""));
			
			for(Answer answer:quotation.getQuotationRequest().getAnswers()){
				if(answer.getAnswer() != null && answer.getAnswer().contains("On-going, paid monthly")){
					quotation.getQuotationOptions().get(i).setPremium(nfm
							.format(Double.parseDouble(quotation.getQuotationOptions()
									.get(i).getPremium())).replace(",", ".").replace("$", "")+" (per monthly excluding 14% VAT)");
				}else if(answer.getAnswer() != null && answer.getAnswer().contains("On-going, paid annually")){
					quotation.getQuotationOptions().get(i).setPremium(nfm
							.format(Double.parseDouble(quotation.getQuotationOptions()
									.get(i).getPremium())).replace(",", ".").replace("$", "")+" (per annually excluding 14% VAT)");
				}else if(answer.getAnswer() != null && answer.getAnswer().contains("Specific Period (Once-Off)")){
					quotation.getQuotationOptions().get(i).setPremium(nfm
							.format(Double.parseDouble(quotation.getQuotationOptions()
									.get(i).getPremium())).replace(",", ".").replace("$", "")+" (per once-off excluding 14% VAT)");
				}
				
			}
		}
		reportData.put("quotation", quotation);
		reportData.put("POLYGON_REPORT_FILE_RESOLVER", fileResolver);

		for (Answer answer : quotation.getQuotationRequest().getAnswers()) {
			if (answer.getAnswer() != null) {
				if (answer.getAnswer().equals("Specific Period (Once-Off)")) {
					this.setQuotationWording("Quote only valid for 7 days "
							+ "and acceptance thereof by underwriters subject to a fully completed risk assessment, proposal form and signed "
							+ "debit order form. Underwriters reserve the right to refuse acceptance of the risk as declared to us.\n");

				} else if (answer.getAnswer().equals("On-going, paid monthly") || answer.getAnswer().equals("On-going, paid annually")) {
					this.setQuotationWording("Quote is valid for 15 days and acceptance thereof by underwriters subject to a fully completed risk assessment, proposal form and signed "
							+ "debit order form. Underwriters reserve the right to refuse acceptance of the risk as declared to us.\n");
				}
			}
		}

		reportData.put("quotationWording", getQuotationWording());

		JRBeanCollectionDataSource quotationOptions = new JRBeanCollectionDataSource(quotation.getQuotationOptions());
		InputStream jasperIS = getClass().getResourceAsStream("/reports/quotationReport.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperIS);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, quotationOptions);

		return JasperExportManager.exportReportToPdf(jasperPrint);

	}


	public byte[] policyScheduleReportPDF(Policy policy) throws JRException, IOException{

		Map<String,Object> reportData = new HashMap<String,Object>();
		reportData.put("policy", policy);
		reportData.put("POLYGON_REPORT_FILE_RESOLVER", fileResolver);
		
		JRBeanCollectionDataSource indemnityOptionsDS = new JRBeanCollectionDataSource(policy.getIndemnityOptions());
		InputStream jasperIS = getClass().getResourceAsStream("/reports/policyReport.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperIS);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, indemnityOptionsDS);

		//		File file = new File("/Policy_Schedule_"+policy.getReference()+".pdf");
		//		JasperExportManager.exportReportToPdfFile(jasperPrint, file.getName());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public String getQuotationWording() {
		return quotationWording;
	}


	public void setQuotationWording(String quotationWording) {
		this.quotationWording = quotationWording;
	}



}
