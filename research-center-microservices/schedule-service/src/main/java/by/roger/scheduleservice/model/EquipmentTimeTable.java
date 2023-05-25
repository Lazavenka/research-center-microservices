package by.roger.scheduleservice.model;

import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentTimeTable {
    private Equipment equipmentDto;
    private final List<EquipmentWorkTimePeriod> workTimePeriods = new ArrayList<>();

    public static final LocalTime START_WORKING_TIME = LocalTime.of(8, 0, 0);
    public static final LocalTime END_WORKING_TIME = LocalTime.of(20, 0, 0);
    public static final int SEVEN_DAYS = 7;
}
