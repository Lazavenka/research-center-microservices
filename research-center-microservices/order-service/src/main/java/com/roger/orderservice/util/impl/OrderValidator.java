package com.roger.orderservice.util.impl;

import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.util.CustomValidator;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator implements CustomValidator {
    @Override
    public boolean validate(SaveOrderDto saveOrderDto) {
        if (saveOrderDto.getClientId() <= 0){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_CLIENT_ID);
        }
        if (saveOrderDto.getEquipmentId() <= 0){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_EQUIPMENT_ID);
        }
        if (saveOrderDto.getRentStartTime().isAfter(saveOrderDto.getRentEndTime())){
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.END_TIME_BEFORE_START_TIME);
        }
        return true;
    }
}
