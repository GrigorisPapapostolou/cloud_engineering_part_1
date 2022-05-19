package com.cloud.engineering.citizen.controller;

import com.cloud.engineering.citizen.dto.OptionDto;
import com.cloud.engineering.citizen.dto.UserLoginDto;
import com.cloud.engineering.citizen.dto.StatementDto;
import com.cloud.engineering.citizen.dto.UserRegistrationDto;
import com.cloud.engineering.citizen.model.UserModel;
import com.cloud.engineering.citizen.service.GovService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.http.HttpRequest;

@Slf4j
@Controller
public class GovController {
    @Autowired
    private GovService govService;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

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
    public String validateUser(@ModelAttribute("userLogin") UserLoginDto loginDto, RedirectAttributes redirectAttributes) {
        log.info("Controller - Perform validation of user");
        UserModel user = govService.findUserByEmail(loginDto.getEmail());
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
        if (payload.contains("statement")) {
            model.addAttribute("statement", new StatementDto());
            model.addAttribute("id", id);
            return "statement";
        } else {
            model.addAttribute("statement", govService.findUserById(Long.parseLong(id)));
            return "marital";
        }
    }

    @GetMapping("/api/v1/gov/statement/{id}")
    public void callStatementService(@PathVariable String id) {
        log.info("Controller -Call statement microservice");
        String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        restTemplate.postForObject("http://localhost:8083/api/v1/statement/download", entity, String.class);
    }

    @GetMapping("/api/v1/gov/marital/{id}")
    public void callMaritalService(@PathVariable String id) {
        log.info("Controller -Call marital microservice");
    }
}
