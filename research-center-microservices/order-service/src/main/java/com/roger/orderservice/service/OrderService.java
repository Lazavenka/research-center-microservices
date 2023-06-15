package com.roger.orderservice.service;

import com.roger.researchcenter.dto.OrderGetDto;
import com.roger.orderservice.dto.SaveOrderDto;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderGetDto createOrder(SaveOrderDto saveOrderDto);

    OrderGetDto getById(Long id);

    List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(Long equipmentId, LocalDateTime startTime, LocalDateTime endTime);

    List<OrderGetDto> getAll();

    OrderGetDto payOrder(Long userId, Long orderId);
}
