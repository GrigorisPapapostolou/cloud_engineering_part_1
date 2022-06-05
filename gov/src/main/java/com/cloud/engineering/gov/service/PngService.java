package com.cloud.engineering.gov.service;

import com.cloud.engineering.clients.marital.MaritalResponseDto;
import com.cloud.engineering.clients.statement.StatementResponseDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import gui.ava.html.image.generator.HtmlImageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;

@Service
@Slf4j
public class PngService {
    public void export_marital_certifcate(HttpServletResponse response, MaritalResponseDto marital) throws IOException {
        log.info("Service - Download Marital PNG");

        // Set up response
        response.setContentType("image/png");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=marital_" + currentDateTime + ".png";
        response.setHeader(headerKey, headerValue);

        String html_start = "<!DOCTYPE html><html><head><style> body {height: 842px;width: 595px;margin-left: auto;margin-right: auto;}</style></head><body>";
        String title = "<h1>Personal Info</h1>";
        String fname = String.format("<p>First Name : %s </p>", marital.getFirstName());
        String lname = String.format("<p>Last Name : %s </p>", marital.getLastName());
        String email = String.format("<p>Email : %s </p>", marital.getEmail());
        String nation = String.format("<p>Nationality : %s </p>", marital.getNationality());
        String city = String.format("<p>City : %s </p>", marital.getCity());
        String html_end = "</body></html>";


        String html = html_start + title + fname + lname + email + nation + city + html_end;
        HtmlImageGenerator hig = new HtmlImageGenerator();
        hig.loadHtml(html);
        ImageIO.write(hig.getBufferedImage(), "PNG", response.getOutputStream());
    }

    public void export_statement_declaration(HttpServletResponse response, StatementResponseDto statementResponseDto) throws IOException {
        log.info("Service - Download Statement PNG");

        // Set up response
        response.setContentType("image/png");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=marital_" + currentDateTime + ".png";
        response.setHeader(headerKey, headerValue);

        String html_start = "<!DOCTYPE html><html><head><style> body {height: 842px;width: 595px;margin-left: auto;margin-right: auto;}</style></head><body>";
        String title = "<h1>Declaration Statement</h1>";
        String fparagraph = "<p>At my own risk and knowing the sanctions, provided by the provisions of par. 6 of article 22 of Law 7845/65978, I declare that:</p>";
        String statement = String.format("<p>Last Name : %s </p>", statementResponseDto.getDeclaration());
        String html_end = "</body></html>";

        String html = html_start + title + fparagraph + statement + html_end;
        HtmlImageGenerator hig = new HtmlImageGenerator();
        hig.loadHtml(html);
        ImageIO.write(hig.getBufferedImage(), "PNG", response.getOutputStream());
    }

}

