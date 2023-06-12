package com.roger.orderservice.util;

import com.roger.orderservice.dto.SaveOrderDto;

public interface CustomValidator {
    boolean validate(SaveOrderDto saveOrderDto);
}
