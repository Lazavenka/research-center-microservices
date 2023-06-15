package com.roger.scheduleservice.controller;

import com.roger.scheduleservice.model.EquipmentTimeTable;
import com.roger.scheduleservice.model.Order;
import com.roger.scheduleservice.service.TimeTableService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/")
@AllArgsConstructor
public class ScheduleController {

    private TimeTableService timeTableService;

    @GetMapping(value = "/equipment/{equipmentId}/schedule")
    public EquipmentTimeTable provideTimeTable(@PathVariable Long equipmentId,
                                               @RequestParam("dateTime") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime) {
        return timeTableService.provideEquipmentTimeTable(equipmentId, startTime);
    }

    @PostMapping(value = "/equipment/{equipmentId}/schedule")
    public Mono<Boolean> isAvailableForOrder(@PathVariable Long equipmentId,
                                             @RequestBody Order order) {

        return timeTableService.isAvailableForOrder(equipmentId, order);
    }
}
