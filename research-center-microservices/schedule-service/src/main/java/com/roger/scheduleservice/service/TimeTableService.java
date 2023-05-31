package com.roger.scheduleservice.service;

import com.roger.scheduleservice.model.EquipmentTimeTable;
import com.roger.scheduleservice.model.Order;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TimeTableService {

    EquipmentTimeTable provideEquipmentTimeTable(Long equipmentId, LocalDateTime dateTime);

    Mono<Boolean> isAvailableForOrder(Long equipmentId, Order order);
}
