package com.roger.researchcenter.config;

import com.roger.researchcenter.exception.ApiErrorResponseStatus;
import com.roger.researchcenter.token.ErrorMessageToJsonConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAccessDeniedToJsonResponseHandler implements AccessDeniedHandler {
    private final MessageSource messageSource;
    private final ErrorMessageToJsonConverter converter;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.ACCESS_DENIED;
        String json = converter.convertException(apiErrorResponseStatus, messageSource, accessDeniedException);
        log.info(json);
        response.setStatus(apiErrorResponseStatus.getHttpStatus().value());
        response.getWriter().write(json);
    }

}
