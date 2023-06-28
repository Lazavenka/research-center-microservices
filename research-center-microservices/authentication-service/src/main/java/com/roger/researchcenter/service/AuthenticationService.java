package com.roger.researchcenter.service;

import com.roger.researchcenter.dto.AuthenticationRequest;
import com.roger.researchcenter.dto.AuthenticationResponse;
import com.roger.researchcenter.dto.RegisterRequest;
import com.roger.researchcenter.model.User;

import java.util.List;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(RegisterRequest request);

    List<User> getUsers();
}
