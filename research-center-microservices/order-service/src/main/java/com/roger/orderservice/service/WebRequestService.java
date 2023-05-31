package com.roger.orderservice.service;

import com.roger.orderservice.dto.EquipmentDto;
import com.roger.orderservice.model.Order;
import reactor.core.publisher.Mono;

public interface WebRequestService {
    Mono<Boolean> requestCheckAvailability(Order order);
    Mono<EquipmentDto> requestEquipmentInfo(Long equipmentId);
}
