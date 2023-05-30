package com.roger.orderservice.service;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.RentPeriodDto;
import com.roger.orderservice.dto.SaveOrderDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderGetDto createOrder(SaveOrderDto saveOrderDto);

    OrderGetDto getById(Long id);

    List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(Long equipmentId, LocalDateTime startTime, LocalDateTime endTime);

}
