package com.roger.researchcenter.service.impl;

import com.roger.researchcenter.dto.AuthenticationRequest;
import com.roger.researchcenter.dto.AuthenticationResponse;
import com.roger.researchcenter.dto.RegisterRequest;
import com.roger.researchcenter.exception.CustomWebServiceException;
import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.researchcenter.model.Roles;
import com.roger.researchcenter.model.User;
import com.roger.researchcenter.model.UserCredentials;
import com.roger.researchcenter.model.UserState;
import com.roger.researchcenter.repository.RoleRepository;
import com.roger.researchcenter.repository.UserRepository;
import com.roger.researchcenter.service.AuthenticationService;
import com.roger.researchcenter.service.RegisterTokenService;
import com.roger.researchcenter.token.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RegisterTokenService registerTokenService;
    private final AuthenticationProvider authenticationProvider;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final EmailConfirmationService emailService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()));
        UserCredentials credentials = new UserCredentials(userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User with name %s not found".formatted(request.getEmail()))));
        String token = jwtTokenUtils.generateJwtToken(credentials);
        return new AuthenticationResponse(token);
    }

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        String userEmail = request.getEmail();
        Optional<User> userByEmail = userRepository.findUserByEmail(userEmail);
        if (userByEmail.isPresent()){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.USERNAME_EXISTS);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.PASSWORD_MISMATCH);
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .roles(List.of(roleRepository.findByRoleName("ROLE_" + Roles.USER.getValue())
                        .orElseThrow(() -> new CustomWebServiceException(ServiceLayerExceptionCodes.INTERNAL_SERVICE_ERROR +
                                " error during reading database. Role \"USER\" not found"))))
                .state(UserState.REGISTRATION)
                .build();
        User createdUser = userRepository.save(user);
        String userRegistrationToken = registerTokenService.createUserRegistrationToken(createdUser);
        // emailService.sendConfirmation(createdUser, userRegistrationToken); todo настроить email клиент
        return new AuthenticationResponse(jwtTokenUtils.generateJwtToken(new UserCredentials(createdUser)) + ":"+userRegistrationToken);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public AuthenticationResponse confirmRegistration(String token){
        User user = registerTokenService.findUserByRegistrationToken(token);
        user.setState(UserState.ACTIVE);
        User savedUser = userRepository.save(user);
        return new AuthenticationResponse(jwtTokenUtils.generateJwtToken(new UserCredentials(savedUser)));
    }

}
