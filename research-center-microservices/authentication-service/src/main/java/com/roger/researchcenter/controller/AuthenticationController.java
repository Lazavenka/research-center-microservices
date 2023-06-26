package com.roger.researchcenter.controller;

import com.roger.researchcenter.dto.AuthenticationRequest;
import com.roger.researchcenter.dto.AuthenticationResponse;
import com.roger.researchcenter.dto.RegisterRequest;
import com.roger.researchcenter.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }



}
