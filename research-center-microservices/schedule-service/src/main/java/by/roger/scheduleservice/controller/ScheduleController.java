package by.roger.scheduleservice.controller;

import by.roger.scheduleservice.dto.EquipmentTimeTableDto;
import by.roger.scheduleservice.dto.RentPeriodDto;
import by.roger.scheduleservice.model.EquipmentTimeTable;
import by.roger.scheduleservice.model.Order;
import by.roger.scheduleservice.service.TimeTableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/")
@AllArgsConstructor
public class ScheduleController {

    private TimeTableService timeTableService;

    @GetMapping(value = "/equipment/{equipmentId}/schedule")
    public EquipmentTimeTable provideTimeTable(@PathVariable Long equipmentId,
                                                  @RequestParam LocalDateTime startTime){
        Optional<EquipmentTimeTable> optionalEquipmentTimeTable
                = timeTableService.provideEquipmentTimeTable(equipmentId, startTime);

        return optionalEquipmentTimeTable.orElseThrow();
    }
    @PostMapping(value = "/equipment/{equipmentId}/schedule")
    public Mono<Boolean> isAvailableForOrder(@PathVariable Long equipmentId,
                                             @RequestBody Order order){

        return timeTableService.isAvailableForOrder(equipmentId, order);
    }
}
