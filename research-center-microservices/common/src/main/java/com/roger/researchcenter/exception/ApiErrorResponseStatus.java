package com.roger.researchcenter.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ApiErrorResponseStatus {
    BAD_REQUEST_EXCEPTION("40000", HttpStatus.BAD_REQUEST),
    INCORRECT_CLIENT_ID("40001", HttpStatus.BAD_REQUEST),
    INCORRECT_EQUIPMENT_ID("40002", HttpStatus.BAD_REQUEST),
    INCORRECT_ID("40003", HttpStatus.BAD_REQUEST),
    INCORRECT_START_END_TIME("40004", HttpStatus.BAD_REQUEST),
    INCORRECT_NAME("40005", HttpStatus.BAD_REQUEST),
    INCORRECT_DESCRIPTION("40006", HttpStatus.BAD_REQUEST),
    INCORRECT_PRICE("40007", HttpStatus.BAD_REQUEST),
    INCORRECT_RESEARCH_TIME("40008", HttpStatus.BAD_REQUEST),
    INCORRECT_ADDRESS("40009", HttpStatus.BAD_REQUEST),
    INCORRECT_LOCATION("40010", HttpStatus.BAD_REQUEST),
    EQUIPMENT_EXISTS("40011", HttpStatus.BAD_REQUEST),
    LABORATORY_EXISTS("40012", HttpStatus.BAD_REQUEST),
    UNAVAILABLE_FOR_ORDER("40013", HttpStatus.BAD_REQUEST),
    INCORRECT_REQUEST_DATA("40099", HttpStatus.BAD_REQUEST),

    NOT_FOUND("40400", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID("40401", HttpStatus.NOT_FOUND),
    NOT_FOUND_EQUIPMENT_ID("40402", HttpStatus.NOT_FOUND),
    NOT_FOUND_LABORATORY_ID("40403", HttpStatus.NOT_FOUND),
    NOT_FOUND_EQUIPMENT_TYPE_ID("40404", HttpStatus.NOT_FOUND),
    NOT_FOUND_DEPARTMENT_ID("40405", HttpStatus.NOT_FOUND),
    NOT_UPDATED_ID("40406", HttpStatus.NOT_FOUND),
    NOT_FOUND_EQUIPMENT("40408", HttpStatus.NOT_FOUND),
    NOT_FOUND_ORDERS("40411", HttpStatus.BAD_REQUEST),

    NOT_FOUND_EXCEPTION("40499", HttpStatus.NOT_FOUND),

    METHOD_NOT_ALLOWED_EXCEPTION("40501", HttpStatus.METHOD_NOT_ALLOWED),

    INTERNAL_SERVER_EXCEPTION("50000", HttpStatus.INTERNAL_SERVER_ERROR),
    READING_FROM_DATABASE_ERROR("50001", HttpStatus.INTERNAL_SERVER_ERROR),
    SAVING_ERROR("50002", HttpStatus.INTERNAL_SERVER_ERROR),
    TRANSACTION_ERROR("50003", HttpStatus.INTERNAL_SERVER_ERROR),
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
