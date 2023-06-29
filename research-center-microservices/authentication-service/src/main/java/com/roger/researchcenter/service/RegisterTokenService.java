package com.roger.researchcenter.service;

import com.roger.researchcenter.model.User;

public interface RegisterTokenService {
    String createUserRegistrationToken(User user);
    User findUserByRegistrationToken(String token);
}
