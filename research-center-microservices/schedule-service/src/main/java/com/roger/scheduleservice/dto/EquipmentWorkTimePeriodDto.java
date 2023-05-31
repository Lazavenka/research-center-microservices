package com.roger.scheduleservice.dto;

import com.roger.scheduleservice.model.EquipmentAvailability;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentWorkTimePeriodDto {
    private LocalDateTime startOfPeriod;
    private LocalDateTime endOfPeriod;
    private EquipmentAvailability availability;
}
