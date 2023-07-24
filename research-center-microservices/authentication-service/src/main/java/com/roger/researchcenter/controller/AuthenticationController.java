package com.roger.researchcenter.controller;

import com.roger.researchcenter.dto.AuthenticationRequest;
import com.roger.researchcenter.dto.AuthenticationResponse;
import com.roger.researchcenter.dto.RegisterRequest;
import com.roger.researchcenter.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }

    @GetMapping(value = "/confirm")
    public ResponseEntity<AuthenticationResponse> confirmRegistration(@RequestParam String token){
        return ResponseEntity.ok(authenticationService.confirmRegistration(token));
    }
}
