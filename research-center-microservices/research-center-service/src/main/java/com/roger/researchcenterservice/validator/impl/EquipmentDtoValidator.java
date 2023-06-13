package com.roger.researchcenterservice.validator.impl;

import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.exception.IncorrectRequestException;
import com.roger.researchcenterservice.service.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.validator.DtoFieldValidator;
import org.springframework.stereotype.Component;

import static com.roger.researchcenterservice.validator.ValidationMethods.*;

@Component
public class EquipmentDtoValidator implements DtoFieldValidator<EquipmentSaveDto> {


    @Override
    public boolean validate(EquipmentSaveDto updateDto) {
        if (updateDto == null) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_REQUEST);
        }
        validateName(updateDto.getName());
        validateDescription(updateDto.getDescription());
        validateIds(updateDto.getLaboratoryId());
        validateIds(updateDto.getEquipmentTypeId());
        validatePrice(updateDto.getPricePerHour());
        validateResearchTime(updateDto.getAverageResearchTime());

        return true;
    }

}
