package com.cloud.engineering.statement.controller;

import com.cloud.engineering.clients.statement.StatementDto;
import com.cloud.engineering.clients.statement.StatementResponseDto;
import com.cloud.engineering.statement.service.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@Controller
public class StatementController {
    @Autowired
    private ResponseService responseService;

    @PostMapping(value = "/api/v1/statement/download/{id}")
    public ResponseEntity<StatementResponseDto> getStatement(@PathVariable("id") String id, @RequestBody StatementDto statementDto) throws IOException, URISyntaxException {
        log.info("Controller - Download Statement PDF");
        var headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        StatementResponseDto doc = this.responseService.getResponse(id, statementDto);
        return ResponseEntity.accepted().headers(headers).body(doc);
    }

}
