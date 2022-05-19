package com.cloud.engineering.statement.controller;

import com.cloud.engineering.statement.dto.StatementDto;
import com.cloud.engineering.statement.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class StatementController {
    @Autowired
    private PdfService pdfService;

    @PostMapping("/api/v1/statement/download/{id}")
    public void generatePDF(HttpServletResponse response, @RequestBody String id) throws IOException {
        log.info("Controller - Download PDF");

        // Set up response
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=statement_" + currentDateTime + ".statement";
        response.setHeader(headerKey, headerValue);

        this.pdfService.export(response, id);
    }
}
