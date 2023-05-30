package com.roger.orderservice.service;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.RentPeriodDto;
import com.roger.orderservice.dto.SaveOrderDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderGetDto createOrder(SaveOrderDto saveOrderDto);

    OrderGetDto getById(Long id);

    List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(Long equipmentId, RentPeriodDto periodDto);

    List<OrderGetDto> getOrdersByAssistantIdAtPeriod(Long assistantId, RentPeriodDto periodDto);
    Map<Long, List<OrderGetDto>> getOrdersByAssistantIdsAtDate(List<Long> assistantIds, LocalDate date);
}
