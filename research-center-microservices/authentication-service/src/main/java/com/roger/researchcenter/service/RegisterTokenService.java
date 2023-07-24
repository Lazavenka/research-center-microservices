package com.roger.researchcenter.service;

import com.roger.researchcenter.model.User;
import com.roger.researchcenter.model.UserRegisterToken;

import java.util.List;

public interface RegisterTokenService {
    String createUserRegistrationToken(User user);
    UserRegisterToken findUserByRegistrationToken(String token);
    void deleteToken(UserRegisterToken token);

    List<UserRegisterToken> getUserTokens();
}
