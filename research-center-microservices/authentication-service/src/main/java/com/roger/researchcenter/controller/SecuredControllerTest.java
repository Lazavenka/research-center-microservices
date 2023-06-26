package com.roger.researchcenter.controller;

import com.roger.researchcenter.model.User;
import com.roger.researchcenter.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/test")
@AllArgsConstructor
public class SecuredControllerTest {

    private AuthenticationService service;

    @GetMapping(value = "/user")
    public String testUser(Principal principal){
        return "Secured user endpoint entered. Welcome %s".formatted(principal.getName());
    }

    @GetMapping(value = "/manager")
    public String testAdmin(@AuthenticationPrincipal UserDetails userDetails){
        return "Secured manager endpoint entered. Welcome %s".formatted(userDetails.getUsername());
    }

    @GetMapping(value = "/get_users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }
}
