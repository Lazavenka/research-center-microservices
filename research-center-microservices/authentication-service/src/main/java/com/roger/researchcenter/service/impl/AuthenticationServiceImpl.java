package com.roger.researchcenter.service.impl;

import com.roger.researchcenter.dto.AuthenticationRequest;
import com.roger.researchcenter.dto.AuthenticationResponse;
import com.roger.researchcenter.dto.RegisterRequest;
import com.roger.researchcenter.model.User;
import com.roger.researchcenter.repository.UserRepository;
import com.roger.researchcenter.service.AuthenticationService;
import com.roger.researchcenter.service.UserCredentials;
import com.roger.researchcenter.token.JwtTokenUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

    private final UserRepository repository;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserCredentials credentials = new UserCredentials(
                repository.findUserByEmail(request.getEmail()).orElseThrow(
                        () -> new UsernameNotFoundException("User with name %s not found".formatted(request.getEmail()))));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserCredentials(repository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + "not found!")));
    }

}
