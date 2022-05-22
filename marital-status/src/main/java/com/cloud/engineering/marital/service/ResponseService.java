package com.cloud.engineering.marital.service;


import lombok.extern.slf4j.Slf4j;
import com.cloud.engineering.clients.marital.MaritalResponseDto;
import com.cloud.engineering.clients.marital.UserDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ResponseService {
    public MaritalResponseDto getResponse(UserDto user) throws IOException {
        log.info("Service - Download PDF");
        // Create pfd
        return  MaritalResponseDto.builder()
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .nationality("Greek")
                .city("Thessaloniki")
                .build();
    }
}
