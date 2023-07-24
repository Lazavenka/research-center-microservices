package com.roger.researchcenter.exception;

import com.roger.researchcenter.token.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
@Slf4j
public class JwtVerifyInterceptor implements HandlerInterceptor {

    private final JwtTokenUtils utils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationToken != null && authorizationToken.startsWith("Bearer")) {
            log.info("-----------------Invoke JwtVerifyInterceptor----------------");
            utils.verify(authorizationToken.substring(7));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
