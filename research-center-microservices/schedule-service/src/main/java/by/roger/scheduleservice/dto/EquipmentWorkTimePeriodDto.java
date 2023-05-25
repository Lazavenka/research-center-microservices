package by.roger.scheduleservice.dto;

import by.roger.scheduleservice.model.Assistant;
import by.roger.scheduleservice.model.EquipmentAvailability;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentWorkTimePeriodDto {
    private LocalDateTime startOfPeriod;
    private LocalDateTime endOfPeriod;
    private EquipmentAvailability availability;
    private final List<Assistant> availableAssistantIdsInPeriod = new ArrayList<>();
}
