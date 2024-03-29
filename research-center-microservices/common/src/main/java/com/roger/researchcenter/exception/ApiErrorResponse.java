package com.roger.researchcenter.exception;

import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ApiErrorResponse class contains localized messages and error codes of thrown exception, which can be sent to view.
 */
@ToString
public class ApiErrorResponse {
    private String errorMessage;
    private String errorCode;
    private String originalErrorMessage;
    private final LocalDateTime timestamp;
    public ApiErrorResponse(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        timestamp = LocalDateTime.now();
    }

    /**
     * Factory method for creation ApiErrorResponse messages from {@link ApiErrorResponseStatus}
     * localized with help {@link MessageSource}.
     *<p>
     * Error messages with appropriate error code (key) must be presented in .properties file
     *
     * @param apiErrorResponseStatus enum which contains error code (key)
     * @return ApiErrorResponse
     *
     */
    public static ApiErrorResponse buildResponse(ApiErrorResponseStatus apiErrorResponseStatus, MessageSource messageSource) {
        String code = apiErrorResponseStatus.getErrorCode();
        String message = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        return new ApiErrorResponse(message, code);
    }

    /**
     * Factory method for creation ApiErrorResponse messages from {@link ApiErrorResponseStatus}
     * localized with help {@link MessageSource}.
     *<p>
     * Error messages with appropriate error code (key) must be presented in .properties file.
     * Method formats message and insert id value instead of %d in error message.
     *
     * @param apiErrorResponseStatus enum which contains error code (key)
     * @param id long value of id to format error message
     * @return ApiErrorResponse
     *
     * @see String#format(String, Object...)
     */
    public static ApiErrorResponse buildResponse(ApiErrorResponseStatus apiErrorResponseStatus, MessageSource messageSource, long id) {
        String code = apiErrorResponseStatus.getErrorCode();
        String message = String.format(messageSource.getMessage(code, null, LocaleContextHolder.getLocale()),id);
        return new ApiErrorResponse(message, code);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getOriginalErrorMessage() {
        return originalErrorMessage;
    }

    public void setOriginalErrorMessage(Throwable originalError) {
        this.originalErrorMessage = originalError.getMessage();
    }
    public void setOriginalErrorMessage(String originalError) {
        this.originalErrorMessage = originalError;
    }

    public String getTimestamp() {
        return timestamp.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
