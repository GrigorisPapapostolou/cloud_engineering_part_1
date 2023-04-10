package com.cloud.engineering.clients.statement;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statement", url = "${clients.statement.url}")
public interface StatementClient {
    @PostMapping(path = "/api/v1/statement/download/{id}")
    public ResponseEntity<StatementResponseDto> getStatement(@PathVariable("id") String id, @RequestBody StatementDto statementDto);
}
