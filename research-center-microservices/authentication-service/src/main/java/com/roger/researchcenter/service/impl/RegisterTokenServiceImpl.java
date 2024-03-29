package com.roger.researchcenter.service.impl;

import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.researchcenter.model.User;
import com.roger.researchcenter.model.UserRegisterToken;
import com.roger.researchcenter.repository.UserTokenRepository;
import com.roger.researchcenter.service.RegisterTokenService;
import com.roger.researchcenter.token.UserRegistrationTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterTokenServiceImpl implements RegisterTokenService {
    private final UserTokenRepository repository;

    @Override
    public String createUserRegistrationToken(User user) {
        UserRegisterToken userRegisterToken = UserRegisterToken.builder()
                .registerDateTime(LocalDateTime.now())
                .user(user)
                .token(UserRegistrationTokenGenerator.generateToken())
                .build();
        UserRegisterToken savedToken = repository.save(userRegisterToken);
        return savedToken.getToken();
    }

    @Override
    public UserRegisterToken findUserByRegistrationToken(String token) {
        return repository.findUserRegisterTokenByToken(token).orElseThrow(
                () -> new IncorrectRequestException(ServiceLayerExceptionCodes.INVALID_REGISTRATION_TOKEN));
    }

    @Override
    public void deleteToken(UserRegisterToken token) {
        repository.delete(token);
    }

    @Override
    public List<UserRegisterToken> getUserTokens() {
        return repository.findAll();
    }

}
