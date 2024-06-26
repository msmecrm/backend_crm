package com.msme.crm.security.controller;

import com.msme.crm.security.dao.AuthenticationRequest;
import com.msme.crm.security.dao.AuthenticationResponse;
import com.msme.crm.security.dao.RegisterRequest;
import com.msme.crm.security.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerRequest(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request)) ;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> registerRequest(@RequestBody AuthenticationRequest request){
         return ResponseEntity.ok(authenticationService.authenticate(request));

    }


}
