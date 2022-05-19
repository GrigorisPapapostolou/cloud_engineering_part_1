package com.cloud.engineering.statement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

@Service
@Slf4j
public class PdfService {
    public void export(HttpServletResponse response, String id) throws IOException {
        log.info("Service - Download PDF");
        // Create pfd
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph title = new Paragraph("Declaration Statement", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        Paragraph paragraph1 = new Paragraph("At my own risk and knowing the sanctions, provided by the provisions of par. 6 of article 22 of Law 7845/65978, I declare that: ", fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);

        fontParagraph.setSize(12);
        Paragraph paragraph2 = new Paragraph(id, fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(title);
        document.add(paragraph1);
        document.add(paragraph2);
        document.close();
    }
}
