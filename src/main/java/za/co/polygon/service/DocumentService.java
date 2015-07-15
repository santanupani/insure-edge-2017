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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;

@Service
public class DocumentService {

    public byte[] generateQuotation(Quotation quotation) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph("To :  " + quotation.getQuotationRequest().getCompanyName() + "              Date: " + quotation.getQuotationRequest().getCreateDate().toString(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("To Whom it may concern : " + quotation.getQuotationRequest().getApplicantName(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph(quotation.getQuotationRequest().getProduct().getName(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));

        for (QuotationOption option : quotation.getQuotationOptions()) {
            document.add(new Paragraph("Locations : " + option.getLocation()));
            document.add(new Paragraph("Limit : " + option.getLimit()));
            document.add(new Paragraph("Commodity : " + option.getCommodity()));
            document.add(new Paragraph("Cover : " + option.getCover()));
            document.add(new Paragraph("Duration/Period : " + option.getPeroid()));
            document.add(new Paragraph("Execess : " + option.getExcess()));
            document.add(new Paragraph("Premium : " + option.getPremium()));
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

        return out.toByteArray();
    }

}
