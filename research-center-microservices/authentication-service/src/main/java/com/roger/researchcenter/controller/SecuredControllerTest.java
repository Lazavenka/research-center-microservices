package com.roger.researchcenter.controller;

import com.roger.researchcenter.model.User;
import com.roger.researchcenter.model.UserRegisterToken;
import com.roger.researchcenter.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/test")
@AllArgsConstructor
public class SecuredControllerTest {

    private AuthenticationService service;

    @GetMapping(value = "/user")
    public String testUser(Authentication authentication) {
        return "Secured user endpoint entered. Welcome %s with role %s".formatted(authentication.getName(), authentication.getAuthorities());
    }

    @GetMapping(value = "/manager")
    public String testAdmin(Authentication authentication) {
        return "Secured manager endpoint entered. Welcome %s with role %s".formatted(authentication.getName(), authentication.getAuthorities());
    }
    //unsecured
    @GetMapping(value = "/get_users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping(value = "/get_tokens")
    public ResponseEntity<List<UserRegisterToken>> getUserTokens() {
        return ResponseEntity.ok(service.getUserTokens());
    }
}
