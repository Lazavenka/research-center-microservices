package com.roger.scheduleservice.model;

import com.roger.scheduleservice.dto.EquipmentDto;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EquipmentTimeTable {
    private EquipmentDto equipment;
    private final List<EquipmentWorkTimePeriod> workTimePeriods = new ArrayList<>();

    public static final LocalTime START_WORKING_TIME = LocalTime.of(8, 0, 0);
    public static final LocalTime END_WORKING_TIME = LocalTime.of(20, 0, 0);
    public static final LocalTime DEFAULT_SERVICE_TIME = LocalTime.of(0, 10, 0);

}
