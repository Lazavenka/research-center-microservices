package com.roger.researchcenterservice.validator.impl;

import com.roger.researchcenterservice.dto.LaboratorySaveDto;
import com.roger.researchcenterservice.exception.IncorrectRequestException;
import com.roger.researchcenterservice.service.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.validator.DtoFieldValidator;
import org.springframework.stereotype.Component;

import static com.roger.researchcenterservice.validator.ValidationMethods.*;

@Component
public class LaboratoryValidator implements DtoFieldValidator<LaboratorySaveDto> {
    @Override
    public boolean validate(LaboratorySaveDto saveUpdateDto) {
        if (saveUpdateDto == null) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_REQUEST);

        }
        validateName(saveUpdateDto.getName());
        validateDescription(saveUpdateDto.getDescription());
        validateLocation(saveUpdateDto.getLocation());

        return true;
    }


}
