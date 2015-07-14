/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.polygon.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;

@Service
public class ReportService {
    
    
    public void buildQuotationPdf(List<QuotationOption> quotationOption, QuotationRequest quotationRequest){
        // TODO : Why passing List<QuotationOption> quotationOption while quotationRequest has the list in it
        // TODO : Why the return type is not byte[] as I specified in the initial requirement
        
        Document document = new Document();
        File repDir = new File("reports");
        File quoteFile = null;
        FileOutputStream fos = null;
        try {
            repDir.mkdir();
            if(repDir.exists()){
                
                quoteFile = new File(repDir,quotationRequest.getApplicantName()+"_quotation.pdf");
//                if(quoteFile.exists()){
//                   //Can be removed....     
//                }
            }else{
            }
            fos = new FileOutputStream(quoteFile);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            document.open();
 
            document.add(new Paragraph("To :  "+ quotationRequest.getCompanyName() +"              Date: " + quotationRequest.getCreateDate().toString(), font1));
             document.add(Chunk.NEWLINE);
            document.add(new Paragraph("To Whom it may concern : " + quotationRequest.getApplicantName(), font1));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(quotationRequest.getProduct().getName(), font1));
            
            for (QuotationOption quote : quotationOption) {
                document.add(new Paragraph("Locations : " + quote.getLocation()));
                document.add(new Paragraph("Limit : " + quote.getLimit()));
                document.add(new Paragraph("Commodity : " + quote.getCommodity()));
                document.add(new Paragraph("Cover : " + quote.getCover()));
                document.add(new Paragraph("Duration/Period : "+ quote.getPeroid()));
                document.add(new Paragraph("Execess : "+ quote.getExcess()));
                document.add(new Paragraph("Premium : " + quote.getPremium()));
                document.add(Chunk.NEWLINE);
             }
            
            document.add(new Paragraph("Quote only valid for 15 days"
                    + "and acceptance thereof by underwriters subject to a fully completed rist assesment, proposal "
                    + "form  and signed debit order form. Underwriters serve the right to refuse"
                    + "acceptance of the risk as declared to us\n"));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Best Regards\n "));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Polygon Team\n "));

            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
