package by.roger.scheduleservice.controller;

import by.roger.scheduleservice.dto.EquipmentTimeTableDto;
import by.roger.scheduleservice.dto.RentPeriodDto;
import by.roger.scheduleservice.model.EquipmentTimeTable;
import by.roger.scheduleservice.service.TimeTableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/equipment/{equipmentId}/schedule")
    public boolean isAvailableForOrder(@PathVariable Long equipmentId,
                                       @RequestParam RentPeriodDto rentPeriod){

        return timeTableService.isAvailableForOrder(equipmentId, rentPeriod);
    }
}
