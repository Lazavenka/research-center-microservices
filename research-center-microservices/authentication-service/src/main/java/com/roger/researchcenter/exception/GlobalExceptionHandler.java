package com.roger.researchcenter.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
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
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.BAD_CREDENTIALS;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.USERNAME_NOT_FOUND;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ApiErrorResponse> handleUnsupportedJwtException(UnsupportedJwtException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.UNSUPPORTED_JWT;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiErrorResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.EXPIRED_JWT;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiErrorResponse> handleSignatureException(SignatureException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.JWT_SIGNATURE;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiErrorResponse> handleMalformedJwtException(MalformedJwtException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.JWT_MALFORMED;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.BAD_REQUEST_EXCEPTION;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(IncorrectRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleIncorrectRequestException(IncorrectRequestException ex){
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.getResponseStatusFromException(ex);
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        apiErrorResponse.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(apiErrorResponse);
    }

    @ExceptionHandler(CustomWebServiceException.class)
    public final ResponseEntity<Object> handleBadRequestException(CustomWebServiceException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.getResponseStatusFromException(ex);
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        apiErrorResponse.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(apiErrorResponse);
    }
}
