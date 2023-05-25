package by.roger.scheduleservice.service;

import by.roger.scheduleservice.dto.EquipmentTimeTableDto;
import by.roger.scheduleservice.dto.RentPeriodDto;
import by.roger.scheduleservice.model.EquipmentTimeTable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeTableService {

    Optional<EquipmentTimeTable> provideEquipmentTimeTable(Long equipmentId, LocalDateTime dateTime);

    boolean isAvailableForOrder(Long equipmentId, RentPeriodDto rentPeriod);
}
