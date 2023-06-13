package com.roger.orderservice.util;

import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.exception.IncorrectRequestException;
import com.roger.orderservice.service.ServiceLayerExceptionCodes;

public interface CustomValidator {
    boolean validate(SaveOrderDto saveOrderDto);
    default void validateId(Long id){
        if (id <= 0){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_ID);
        }
    }

}
