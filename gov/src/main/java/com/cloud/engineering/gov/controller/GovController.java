package com.cloud.engineering.gov.controller;

import com.cloud.engineering.clients.marital.MaritalClient;
import com.cloud.engineering.clients.marital.MaritalResponseDto;
import com.cloud.engineering.clients.marital.UserDto;
import com.cloud.engineering.clients.statement.StatementClient;
import com.cloud.engineering.clients.statement.StatementDto;
import com.cloud.engineering.clients.statement.StatementResponseDto;
import com.cloud.engineering.gov.dto.*;
import com.cloud.engineering.gov.service.GovService;
import com.cloud.engineering.gov.service.PdfService;
import com.cloud.engineering.gov.service.PngService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class GovController {
    @Autowired
    private GovService govService;

    @Autowired
    private PdfService pdfService;
    @Autowired
    private PngService pngService;

    @Autowired
    private StatementClient statementClient;

    @Autowired
    private MaritalClient maritalClient;

    @GetMapping("/api/v1/gov/login")
    public String getLoginForm(Model model) {
        log.info("Controller - Get Login Form");
        model.addAttribute("userLogin", new UserLoginDto());
        return "login";
    }

    @GetMapping("/api/v1/gov/registration")
    public String getRegistrationForm(Model model) {
        log.info("Controller - Get Registration Form");
        model.addAttribute("userRegistration", new UserRegistrationDto());
        return "registration";
    }

    @GetMapping("/api/v1/gov/options/{id}")
    public String getOptions(@PathVariable String id, Model model) {
        log.info("Controller - Get Options Form");
        model.addAttribute("id", id);
        return "options";
    }

    @PostMapping("/api/v1/gov/login")
    public String validateUser(@ModelAttribute("userLogin") UserLoginDto loginDto) {
        log.info("Controller - Perform validation of user");
        UserDto user = govService.findUserByEmail(loginDto.getEmail());
        if (user != null) {
            return "redirect:/api/v1/gov/options/" + user.getId().toString();
        } else {
            return "redirect:/api/v1/gov/registration?error";
        }
    }

    @PostMapping("/api/v1/gov/registration")
    public String registerUser(@ModelAttribute("userRegistration") UserRegistrationDto registrationDto) {
        log.info("Controller - Perform registration of user");
        boolean res = govService.registerCitizen(registrationDto);
        if (res) {
            return "redirect:/api/v1/gov/login?success";
        } else {
            return "redirect:/api/v1/gov/registration?error";
        }
    }
    @PostMapping("/api/v1/gov/options/{id}")
    public String callStatementService(@RequestBody String payload, @PathVariable String id, Model model) {
        log.info("Controller -Call requested microservice");
        model.addAttribute("id", id);
        if (payload.contains("statement")) {
            model.addAttribute("statement", new StatementDto());
            return "statement";
        } else {
            model.addAttribute("marital", govService.findUserById(Long.parseLong(id)));
            return "marital";
        }
    }

    @PostMapping("/api/v1/gov/statement/{id}/pdf")
    public void callStatementPdfService(HttpServletResponse response, @RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute("statement") StatementDto statementDto) throws IOException {
        log.info("Controller - Call statement microservice PDF");
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<StatementResponseDto> res = statementClient.getStatement(id, statementDto);
        this.pdfService.export_statement_declaration(response, res.getBody());
    }

    @PostMapping("/api/v1/gov/statement/{id}/image")
    public void callStatementImageService(HttpServletResponse response, @RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute("statement") StatementDto statementDto) throws IOException {
        log.info("Controller - Call statement microservice PNG");
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<StatementResponseDto> res = statementClient.getStatement(id, statementDto);
        this.pngService.export_statement_declaration(response, res.getBody());
    }

    @PostMapping("/api/v1/gov/marital/{id}/pdf")
    public void callMaritalServicePdf(HttpServletResponse response, @RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute("com/cloud/engineering/clients/marital") UserDto userDto) throws IOException {
        log.info("Controller - Call marital microservice PDF");
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<MaritalResponseDto> res = maritalClient.getMarital(id, userDto);
        this.pdfService.export_marital_certificate(response, res.getBody());
    }

    @PostMapping("/api/v1/gov/marital/{id}/image")
    public void callMaritalServiceImage(HttpServletResponse response, HttpServletRequest request, @RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute("com/cloud/engineering/clients/marital") UserDto userDto) throws IOException {
        log.info("Controller - Call marital microservice PNG");
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<MaritalResponseDto> res = maritalClient.getMarital(id, userDto);
        this.pngService.export_marital_certifcate(response, res.getBody());
    }
}
