package by.roger.scheduleservice.service;

import by.roger.scheduleservice.model.EquipmentTimeTable;
import by.roger.scheduleservice.model.Order;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeTableService {

    EquipmentTimeTable provideEquipmentTimeTable(Long equipmentId, LocalDateTime dateTime);

    Mono<Boolean> isAvailableForOrder(Long equipmentId, Order order);
}
