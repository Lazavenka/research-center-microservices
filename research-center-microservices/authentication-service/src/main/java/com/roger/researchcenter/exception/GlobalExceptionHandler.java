package com.roger.researchcenter.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex){
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.BAD_CREDENTIALS;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> usernameNotFoundException(UsernameNotFoundException ex){
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.USERNAME_NOT_FOUND;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }


}
