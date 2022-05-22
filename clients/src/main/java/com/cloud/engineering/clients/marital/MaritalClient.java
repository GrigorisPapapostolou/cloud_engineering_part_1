package com.cloud.engineering.clients.marital;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "MARITAL")
public interface MaritalClient {
    @PostMapping(value = "/api/v1/marital/download/{id}")
    public ResponseEntity<MaritalResponseDto> getMarital(@PathVariable("id") String id, @RequestBody UserDto userDto);

}
