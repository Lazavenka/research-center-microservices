package com.roger.scheduleservice.service;

import com.roger.scheduleservice.model.EquipmentTimeTable;
import com.roger.scheduleservice.model.Order;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TimeTableService {

    EquipmentTimeTable provideEquipmentTimeTable(Long equipmentId, LocalDate selectedDay);

    Mono<Boolean> isAvailableForOrder(Long equipmentId, Order order);
}
