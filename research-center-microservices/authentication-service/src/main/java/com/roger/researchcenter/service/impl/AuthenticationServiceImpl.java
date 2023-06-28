package com.roger.researchcenter.service.impl;

import com.roger.researchcenter.dto.AuthenticationRequest;
import com.roger.researchcenter.dto.AuthenticationResponse;
import com.roger.researchcenter.dto.RegisterRequest;
import com.roger.researchcenter.model.User;
import com.roger.researchcenter.repository.UserRepository;
import com.roger.researchcenter.service.AuthenticationService;
import com.roger.researchcenter.service.UserCredentials;
import com.roger.researchcenter.token.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final AuthenticationProvider authenticationProvider;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()));
        UserCredentials credentials = new UserCredentials(repository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User with name %s not found".formatted(request.getEmail()))));
        String token = jwtTokenUtils.generateJwtToken(credentials);
        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }


}
