package com.roger.researchcenterservice.validator;

import com.roger.researchcenterservice.dto.DtoEntity;
import com.roger.researchcenterservice.exception.IncorrectRequestException;
import com.roger.researchcenterservice.service.ServiceLayerExceptionCodes;

public interface DtoFieldValidator<T extends DtoEntity> {
    boolean validate(T updateDto);
    default void validateId(Long id){
        if (id <= 0){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_ID);
        }
    }
}
