package com.roger.researchcenter.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.roger.researchcenter.exception.ApiErrorResponse;
import com.roger.researchcenter.exception.ApiErrorResponseStatus;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageToJsonConverter {
    private final JsonMapper mapper = JsonMapper.builder()
            .enable(JsonWriteFeature.QUOTE_FIELD_NAMES,
                    JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS)
            .build();

    public String convertException(ApiErrorResponseStatus apiErrorResponseStatus, MessageSource messageSource, Exception exception) throws JsonProcessingException {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.buildResponse(apiErrorResponseStatus, messageSource);
        apiErrorResponse.setOriginalThrowable(exception);
        return mapper.writeValueAsString(apiErrorResponse);
    }

}
