package com.roger.researchcenterservice.validator.impl;

import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.dto.EquipmentTypeSaveDto;
import com.roger.researchcenterservice.validator.DtoFieldValidator;
import org.springframework.stereotype.Component;

import static com.roger.researchcenterservice.validator.ValidationMethods.validateDescription;
import static com.roger.researchcenterservice.validator.ValidationMethods.validateName;

@Component
public class EquipmentTypeDtoValidator implements DtoFieldValidator<EquipmentTypeSaveDto> {
    @Override
    public boolean validate(EquipmentTypeSaveDto updateDto) {
        if (updateDto == null) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_REQUEST);

        }
        validateName(updateDto.getName());
        validateDescription(updateDto.getDescription());
        return true;
    }

}
