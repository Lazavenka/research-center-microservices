package com.roger.researchcenterservice.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(CustomNotFoundException ex){
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
        return ResponseEntity.status(apiErrorResponseStatus.getHttpStatus())
                .body(apiErrorResponse);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class})
    public ResponseEntity<Object> handleBadArgumentsException(JsonProcessingException exception) {
        ApiErrorResponseStatus responseStatus =  ApiErrorResponseStatus.INCORRECT_REQUEST_DATA;
        return ResponseEntity.status(responseStatus.getHttpStatus())
                .body(ApiErrorResponse.buildResponse(responseStatus, messageSource));
    }

}
