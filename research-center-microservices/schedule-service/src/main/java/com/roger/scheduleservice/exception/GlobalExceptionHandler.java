package com.roger.scheduleservice.exception;

import com.roger.microservices.exception.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    private MessageSource messageSource;

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomNotFoundException(CustomNotFoundException ex){
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.getResponseStatusFromException(ex);
        long notFoundId = ex.getId();
        ApiErrorResponse apiErrorResponse = notFoundId > 0 ? ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource, notFoundId)
                : ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(apiErrorResponse);
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
    public final ResponseEntity<Object> handleCustomWebServiceException(CustomWebServiceException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.getResponseStatusFromException(ex);
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        apiErrorResponse.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(apiErrorResponse);
    }

    @ExceptionHandler(ConnectException.class)
    public final ResponseEntity<Object> handleServiceConnectException(ConnectException ex){
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.WEB_SERVICE_UNAVAILABLE;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.METHOD_NOT_ALLOWED_EXCEPTION;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(status).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleHttpMediaTypeNotSupported ", ex);
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleHttpMediaTypeNotAcceptable ",ex);

        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleMissingPathVariable "+ex);

        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleMissingServletRequestParameter ",ex);

        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleMissingServletRequestPart ",ex);

        return super.handleMissingServletRequestPart(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleServletRequestBindingException ",ex);

        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleMethodArgumentNotValid",ex);
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleNoHandlerFoundException ",ex);
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleAsyncRequestTimeoutException ", ex);

        return super.handleAsyncRequestTimeoutException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleErrorResponseException " ,ex);
        return super.handleErrorResponseException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleConversionNotSupported ",ex);
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.BAD_REQUEST_EXCEPTION;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalThrowable(ex);
        return ResponseEntity.status(status).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleHttpMessageNotReadable ", ex);
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug("handleHttpMessageNotWritable ", ex);
        return super.handleHttpMessageNotWritable(ex, headers, status, request);
    }

}
