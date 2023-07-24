package com.roger.researchcenterservice.validator;

import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;

import java.math.BigDecimal;
import java.time.LocalTime;

import static com.roger.researchcenterservice.validator.ValidationConstants.*;

public class ValidationMethods {
    private ValidationMethods() {
    }

    public static void validateAddress(String address) {
        if (address == null || address.length() < MIN_TEXT_LENGTH
                || address.length() > MAX_VARCHAR255_LENGTH) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_NAME);
        }
    }

    public static void validateLocation(String location) {
        if (location == null || location.length() < MIN_TEXT_LENGTH
                || location.length() > MAX_VARCHAR255_LENGTH) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_LOCATION);
        }
    }

    public static void validateDescription(String description) {
        if (description == null || description.length() < MIN_TEXT_LENGTH) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_DESCRIPTION);
        }
    }

    public static void validateName(String name) {
        if (name == null || name.length() < MIN_TEXT_LENGTH
                || name.length() > MAX_VARCHAR255_LENGTH) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_ADDRESS);
        }
    }

    public static void validateIds(long id) {
        if (id <= 0) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_ID);
        }
    }

    public static void validateResearchTime(LocalTime researchTime) {
        if (researchTime == null || researchTime.isAfter(MAX_RESEARCH_TIME) || researchTime.isBefore(MIN_RESEARCH_TIME)) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_RESEARCH_TIME);
        }
    }

    public static void validatePrice(BigDecimal price) {
        if (price == null || (price.compareTo(MIN_PRICE) < 0 || price.compareTo(MAX_PRICE) > 0)) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_PRICE);
        }
    }
}
