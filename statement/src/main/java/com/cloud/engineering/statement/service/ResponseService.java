package com.cloud.engineering.statement.service;

import com.cloud.engineering.clients.statement.StatementDto;
import com.cloud.engineering.clients.statement.StatementResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ResponseService {
    public StatementResponseDto getResponse(String id, StatementDto statementDto) throws IOException {
        log.info("Service - Get PDF");
        // Create pfd
        return new StatementResponseDto(statementDto.getStatement());
    }
}
