package com.roger.researchcenter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class TestAuthConfigurer extends AbstractHttpConfigurer<TestAuthConfigurer, HttpSecurity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestAuthConfigurer.class);

    private AuthenticationEntryPoint entryPoint = (request, response, authException) -> {
        response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "JWT");
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    };


    @Override
    public void init(HttpSecurity builder){
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        LOGGER.info("Auth manager in init of AbstractHttpConfigurer is {}", authenticationManager);
    }

    @Override
    public void configure(HttpSecurity builder){
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, new TestAuthenticationConverter());
        authenticationFilter.setSuccessHandler(((request, response, authentication) -> {}));
        authenticationFilter.setFailureHandler(new AuthenticationEntryPointFailureHandler(this.entryPoint));

        builder.addFilterBefore(authenticationFilter,BasicAuthenticationFilter.class);
    }

    public TestAuthConfigurer authenticationEntryPoint(AuthenticationEntryPoint entryPoint){
        this.entryPoint = entryPoint;
        return this;
    }
}
