/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.polygon.service;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;

@Service
public class DocumentService extends PdfPageEventHelper{

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

            document.add(new Phrase("Locations : "));
            document.add(Chunk.TABBING);
            document.add(Chunk.TABBING);
            document.add(new Phrase(option.getLocation(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("Limit ZAR  "));
            document.add(Chunk.TABBING);
            document.add(Chunk.TABBING);
            document.add(new Phrase(option.getLimit()));
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
            document.add(new Phrase("Execess : "));
            document.add(Chunk.TABBING);
            document.add(Chunk.TABBING);
            document.add(new Phrase(option.getExcess()));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("Premium ZAR"));
            document.add(Chunk.TABBING);
            document.add(new Phrase(option.getPremium()));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
        }

        document.add(new Paragraph("Quote only valid for 15 days"
                + "and acceptance thereof by underwriters subject to a fully completed rist assesment, proposal "
                + "form  and signed debit order form. Underwriters serve the right to refuse"
                + "acceptance of the risk as declared to us\n"));
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
                footer = Image.getInstance("src/main/webapp/img/products/polygon-footer.png");
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
                img = Image.getInstance("src/main/webapp/img/products/polygon-logo.jpg");
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
