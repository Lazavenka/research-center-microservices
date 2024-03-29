package com.roger.orderservice.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roger.researchcenter.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(CustomNotFoundException ex){
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.getResponseStatusFromException(ex);
        Long notFoundId = ex.getId();
        ApiErrorResponse apiErrorResponse = notFoundId > 0 ? ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource, notFoundId)
                : ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(apiErrorResponse);
    }

    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<ApiErrorResponse> handleWebClientException(WebClientException ex){
        String exMessage = ex.getMessage();
        ApiErrorResponse response = new ApiErrorResponse(exMessage, ServiceLayerExceptionCodes.INTERNAL_SERVICE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(IncorrectRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleIncorrectRequestException(IncorrectRequestException ex){
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.getResponseStatusFromException(ex);
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(apiErrorResponse);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class})
    public ResponseEntity<Object> handleBadArgumentsException(JsonProcessingException exception) {
        ApiErrorResponseStatus responseStatus =  ApiErrorResponseStatus.INCORRECT_REQUEST_DATA;
        return ResponseEntity.status(responseStatus.getHttpStatus())
                .body(ApiErrorResponse.buildResponse(responseStatus, messageSource));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.METHOD_NOT_ALLOWED_EXCEPTION;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalErrorMessage(ex);
        return ResponseEntity.status(status).body(response);
    }
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.BAD_REQUEST_EXCEPTION;
        ApiErrorResponse response = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        response.setOriginalErrorMessage(ex);
        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(CustomWebServiceException.class)
    public final ResponseEntity<Object> handleBadRequestException(CustomWebServiceException ex) {
        ApiErrorResponseStatus apiErrorResponseStatus = ApiErrorResponseStatus.getResponseStatusFromException(ex);
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(apiErrorResponse);
    }
}
