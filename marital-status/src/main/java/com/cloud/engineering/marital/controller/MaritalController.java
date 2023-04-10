package com.cloud.engineering.marital.controller;

import com.cloud.engineering.marital.service.ResponseService;
import lombok.extern.slf4j.Slf4j;
import com.cloud.engineering.clients.marital.MaritalResponseDto;
import com.cloud.engineering.clients.marital.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Controller
public class MaritalController {
    @Autowired
    private ResponseService responseService;

    @PostMapping(value = "/api/v1/marital/download/{id}")
    public ResponseEntity<MaritalResponseDto> getMarital(@PathVariable("id") String id, @RequestBody UserDto userDto) throws IOException {
        log.info("Controller - Download Marital PDF");
        var headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        MaritalResponseDto res = this.responseService.getResponse(userDto);
        return ResponseEntity.accepted().headers(headers).body(res);
    }
}
