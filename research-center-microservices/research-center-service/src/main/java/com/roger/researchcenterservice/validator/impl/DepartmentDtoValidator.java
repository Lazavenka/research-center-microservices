package com.roger.researchcenterservice.validator.impl;

import com.roger.researchcenterservice.dto.DepartmentSaveDto;
import com.roger.researchcenterservice.exception.IncorrectRequestException;
import com.roger.researchcenterservice.service.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.validator.DtoFieldValidator;
import org.springframework.stereotype.Component;

import static com.roger.researchcenterservice.validator.ValidationMethods.*;

@Component
public class DepartmentDtoValidator implements DtoFieldValidator<DepartmentSaveDto> {
    @Override
    public boolean validate(DepartmentSaveDto updateDto) {
        if (updateDto == null) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_REQUEST);
        }
        validateName(updateDto.getName());
        validateDescription(updateDto.getDescription());
        validateAddress(updateDto.getAddress());

        return true;
    }


}
