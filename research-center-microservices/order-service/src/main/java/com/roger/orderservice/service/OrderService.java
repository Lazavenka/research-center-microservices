package com.roger.orderservice.service;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.RentPeriodDto;
import com.roger.orderservice.dto.SaveOrderDto;

import java.util.List;

public interface OrderService {
    OrderGetDto createOrder(SaveOrderDto saveOrderDto);

    OrderGetDto getById(Long id);

    List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(Long equipmentId, RentPeriodDto periodDto);

    List<OrderGetDto> getOrdersByAssistantIdAtPeriod(Long assistantId, RentPeriodDto periodDto);
}
