/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.polygon.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

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
public class DocumentService extends PdfPageEventHelper {

	public File policyScheduleReportPDF(Policy policy) throws JRException, IOException{

		FileResolver fileResolver = new FileResolver() {

			public File resolveFile(String fileName) {
				URI uri;
				try {
					uri = new URI(this.getClass().getResource(fileName).getPath());
					return new File(uri.getPath());
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		};

		Map<String,Object> reportData = new HashMap<String,Object>();
		reportData.put("policy", policy);
		reportData.put("POLYGON_REPORT_FILE_RESOLVER", fileResolver);

		JRBeanCollectionDataSource indemnityOptionsDS = new JRBeanCollectionDataSource(policy.getIndemnityOptions());
		InputStream jasperIS = getClass().getResourceAsStream("/static/reports/policyScheduleReport1.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperIS);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, indemnityOptionsDS);

		File file = new File("/static/reports/Policy_Schedule_"+policy.getPolicyReference()+".pdf");
		JasperExportManager.exportReportToPdfFile(jasperPrint, file.getName());

		return file;
	}
	
    public byte[] generateQuotation(Quotation quotation) throws DocumentException, BadElementException, IOException {
        Document document = new Document(PageSize.A4);
        document.setMargins(50, 45, 50, 30);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setPageEvent(this);
        document.open();

        document.add(new Paragraph(" "));
        document.add(Chunk.NEWLINE);
        document.add(new Phrase("To :  "));
        document.add(new Phrase(quotation.getQuotationRequest().getCompanyName(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        document.add(Chunk.TABBING);
        document.add(Chunk.TABBING);
        document.add(Chunk.TABBING);
        document.add(Chunk.TABBING);
        document.add(new Phrase("Date : "));
        document.add(new Phrase(quotation.getQuotationRequest().getCreateDate().toString(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(new Phrase("Refence Number :"));
        document.add(Chunk.TABBING);
        document.add(new Phrase(quotation.getQuotationRequest().getReference(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("To Whom it may concern : " + quotation.getQuotationRequest().getApplicantName(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph(quotation.getQuotationRequest().getProduct().getName(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        document.add(Chunk.NEWLINE);

        for (QuotationOption option : quotation.getQuotationOptions()) {

            if (option.getPavements() == null) {
                document.add(new Phrase("Locations : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getLocation(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
                if (quotation.getQuotationRequest().getProduct().getId() != 4) {
                    document.add(new Phrase("Limit ZAR"));
                    document.add(Chunk.TABBING);
                    document.add(Chunk.TABBING);
                    document.add(Chunk.TABBING);
                    document.add(new Phrase(option.getLimit() + " per vehicle per transit"));
                } else if (quotation.getQuotationRequest().getProduct().getId() == 4) {

                    document.add(new Phrase("Limit in Transit ZAR"));
                    document.add(Chunk.TABBING);
                    document.add(Chunk.TABBING);
                    document.add(new Phrase(option.getLimit() + " per vehicle per transit"));
                    document.add(Chunk.NEWLINE);
                    document.add(new Phrase("Limit in Static ZAR"));
                    document.add(Chunk.TABBING);
                    document.add(Chunk.TABBING);
                    document.add(new Phrase(option.getStaticLimit() + " per vehicle per transit"));
                    document.add(Chunk.NEWLINE);

                }
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Commodity : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getCommodity()));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Cover : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getCover()));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Duration : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getDuration() + " X Weekly."));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Execess : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getExcess()));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Premium ZAR"));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getPremium() + " p/m ex Vat."));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
            } else {

                document.add(new Phrase("Locations : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getLocation(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
                document.add(Chunk.NEWLINE);
                if (quotation.getQuotationRequest().getProduct().getId() != 4) {
                    document.add(new Phrase("Limit ZAR"));
                    document.add(Chunk.TABBING);
                    document.add(Chunk.TABBING);
                    document.add(Chunk.TABBING);
                    document.add(new Phrase(option.getLimit() + " per vehicle per transit"));
                } else if (quotation.getQuotationRequest().getProduct().getId() == 4) {

                    document.add(new Phrase("Limit in Transit ZAR"));
                    document.add(Chunk.TABBING);
                    document.add(Chunk.TABBING);
                    document.add(new Phrase(option.getLimit() + " per vehicle per transit"));
                    document.add(Chunk.NEWLINE);
                    document.add(new Phrase("Limit in Static ZAR"));
                    document.add(Chunk.TABBING);
                    document.add(Chunk.TABBING);
                    document.add(new Phrase(option.getStaticLimit() + " per vehicle per transit"));
                    document.add(Chunk.NEWLINE);

                }
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Cross Payment ZAR"));
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getPavements() + " per vehicle per transit"));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Commodity : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getCommodity()));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Cover : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getCover()));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Duration : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getDuration()));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Execess : "));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getExcess()));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("Premium ZAR"));
                document.add(Chunk.TABBING);
                document.add(Chunk.TABBING);
                document.add(new Phrase(option.getPremium()));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);

            }
        }

        for (Answer answer : quotation.getQuotationRequest().getAnswers()) {
            if (answer.getAnswer() != null) {
                if (answer.getAnswer().equals("Once-Off")) {

                    document.add(new Paragraph("Quote only valid for 7 days"
                            + " and acceptance thereof by underwriters subject to a fully completed rist assesment, proposal "
                            + "form  and signed debit order form. Underwriters serve the right to refuse"
                            + "acceptance of the risk as declared to us\n"));
                    break;
                } else if (answer.getAnswer().equals("Annually") || answer.getAnswer().equals("Monthly")) {

                    document.add(new Paragraph("Quote only valid for 15 days"
                            + " and acceptance thereof by underwriters subject to a fully completed rist assesment, proposal "
                            + "form  and signed debit order form. Underwriters serve the right to refuse"
                            + "acceptance of the risk as declared to us\n"));
                    break;
                }
            }
        }

        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Best Regards\n "));
        document.add(new Paragraph("Polygon Team\n "));

        document.close();
        writer.close();

        return out.toByteArray();
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        Image footer = null;
        try {
            // TODO : Fix it (should not refer to any path try with classpath resource)
            //footer = Image.getInstance("src/main/resources/static/img/products/polygon-footer.png");
            footer = Image.getInstance(this.getClass().getResource("/static/img/products/polygon-footer.png"));
        } catch (BadElementException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        footer.setAbsolutePosition(0, 5);
        footer.scaleAbsolute(600f, 50f);
        try {
            document.add(footer);
        } catch (DocumentException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        }

        Image img = null;
        try {
            img = Image.getInstance(this.getClass().getResource("/static/img/products/polygon-logo.jpg"));
        } catch (BadElementException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        img.setAbsolutePosition(10, 780);
        img.scaleAbsolute(580f, 50f);
        try {
            document.add(img);
        } catch (DocumentException ex) {
            Logger.getLogger(DocumentService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
