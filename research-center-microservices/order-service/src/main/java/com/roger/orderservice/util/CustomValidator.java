package com.roger.orderservice.util;

import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.orderservice.dto.SaveOrderDto;

public interface CustomValidator {
    boolean validate(SaveOrderDto saveOrderDto);
    default void validateId(Long id){
        if (id <= 0){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_ID);
        }
    }

}
