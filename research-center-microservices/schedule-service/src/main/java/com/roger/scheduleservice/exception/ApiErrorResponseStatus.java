package com.roger.scheduleservice.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ApiErrorResponseStatus {
    BAD_REQUEST_EXCEPTION("40000", HttpStatus.BAD_REQUEST),
    INCORRECT_START_END_TIME("40004", HttpStatus.BAD_REQUEST),

    INCORRECT_EQUIPMENT_ID("40010", HttpStatus.BAD_REQUEST),
    INCORRECT_REQUEST_DATA("40099", HttpStatus.BAD_REQUEST),

    NOT_FOUND("40400", HttpStatus.NOT_FOUND),
    NOT_FOUND_EQUIPMENT("40401", HttpStatus.NOT_FOUND),
    NOT_FOUND_ORDERS("40411", HttpStatus.BAD_REQUEST),
    NOT_FOUND_EXCEPTION("40499", HttpStatus.NOT_FOUND),

    METHOD_NOT_ALLOWED_EXCEPTION("40501", HttpStatus.METHOD_NOT_ALLOWED),

    INTERNAL_SERVER_EXCEPTION("50000", HttpStatus.INTERNAL_SERVER_ERROR),

    WEB_SERVICE_UNAVAILABLE("50099", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String errorCode;
    private final HttpStatus httpStatus;

    ApiErrorResponseStatus(String errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;

    }

    public String getErrorCode() {
        return errorCode;
    }
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
    /**
     * Method for creating ApiErrorResponseStatus from exception which contains custom error code.
     *<p>
     * Method extracts exception message and checks if it contains any error code presented
     * in {@link ApiErrorResponseStatus}. If method doesn't find any then returns {@link ApiErrorResponseStatus#INTERNAL_SERVER_EXCEPTION}
     *
     * @param exception an exception passed in parameters must contain error code in message
     * @return ApiErrorResponseStatus enum of presented in exception error code
     */

    public static ApiErrorResponseStatus getResponseStatusFromException(Exception exception){
        String exceptionMessage = exception.getMessage();
        return Arrays.stream(ApiErrorResponseStatus.values())
                .filter(status -> exceptionMessage.contains(status.getErrorCode()))
                .findAny().orElse(INTERNAL_SERVER_EXCEPTION);
    }
}
