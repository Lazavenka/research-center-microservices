package com.roger.researchcenterservice.validator;

import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.dto.DtoEntity;

public interface DtoFieldValidator<T extends DtoEntity> {
    boolean validate(T updateDto);
    default void validateId(Long id){
        if (id <= 0){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_ID);
        }
    }
}
