package com.kvnbg.securepasswordapi.controller;

import com.kvnbg.securepasswordapi.controller.dtos.BodyRequest;
import com.kvnbg.securepasswordapi.controller.dtos.FailureResponse;
import com.kvnbg.securepasswordapi.service.PasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping
    public ResponseEntity<FailureResponse> check(@RequestBody BodyRequest request) {

        if(request.password() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        var failures = passwordService.check(request.password());

        if(failures.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailureResponse(failures));
    }
}
