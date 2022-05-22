package com.cloud.engineering.gov.service;

import com.cloud.engineering.clients.marital.MaritalResponseDto;
import com.cloud.engineering.clients.statement.StatementResponseDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class PdfService {
    public void export_marital_certifcate(HttpServletResponse response, MaritalResponseDto marital) throws IOException {
        log.info("Service - Download Marital PDF");

        // Set up response
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=marital_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        // Create PDF
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph title = new Paragraph("Personal Info", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        Paragraph fname = new Paragraph("First Name : " + marital.getFirstName(), fontParagraph);
        fname.setAlignment(Paragraph.ALIGN_LEFT);

        fontParagraph.setSize(12);
        Paragraph lname = new Paragraph("Last Name : " + marital.getLastName(), fontParagraph);
        lname.setAlignment(Paragraph.ALIGN_LEFT);

        fontParagraph.setSize(12);
        Paragraph email = new Paragraph("Email : " + marital.getEmail(), fontParagraph);
        email.setAlignment(Paragraph.ALIGN_LEFT);

        fontParagraph.setSize(12);
        Paragraph nationality = new Paragraph("Nationality : " + marital.getNationality(), fontParagraph);
        nationality.setAlignment(Paragraph.ALIGN_LEFT);

        fontParagraph.setSize(12);
        Paragraph city = new Paragraph("City : " + marital.getCity(), fontParagraph);
        city.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(title);
        document.add(fname);
        document.add(lname);
        document.add(email);
        document.add(nationality);
        document.add(city);

        document.close();
    }


    public void export_statement_declaration(HttpServletResponse response, StatementResponseDto statementResponseDto) throws IOException {
        log.info("Service - Download PDF");

        // Set up response
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=statement_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        // Create pfd
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph title = new Paragraph(statementResponseDto.getTitle(), fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        Paragraph paragraph1 = new Paragraph(statementResponseDto.getParagraph(), fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);

        fontParagraph.setSize(12);
        Paragraph paragraph2 = new Paragraph(statementResponseDto.getDeclaration(), fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(title);
        document.add(paragraph1);
        document.add(paragraph2);
        document.close();
    }
}
