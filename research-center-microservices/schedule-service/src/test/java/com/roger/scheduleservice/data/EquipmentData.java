package com.roger.scheduleservice.data;

import com.roger.microservices.dto.EquipmentDto;

import java.math.BigDecimal;
import java.time.LocalTime;

public class EquipmentData {
    public static EquipmentDto EQUIPMENT_ONE_HOUR_RESEARCH_TIME =
            EquipmentDto.builder()
                    .averageResearchTime(LocalTime.of(1,0))
                    .id(1L)
                    .name("Some Test Equipment")
                    .description("Equipment with 1 hour average research time")
                    .imageFilePath("/somewhere")
                    .laboratoryId(1L)
                    .pricePerHour(new BigDecimal("100.00"))
                    .build();

    public static EquipmentDto EQUIPMENT_TWO_HOUR_RESEARCH_TIME =
            EquipmentDto.builder()
                    .averageResearchTime(LocalTime.of(2,0))
                    .id(2L)
                    .name("Another Test Equipment")
                    .description("Equipment with 2 hour average research time")
                    .imageFilePath("/somewhere_else")
                    .laboratoryId(2L)
                    .pricePerHour(new BigDecimal("200.00"))
                    .build();

    public static EquipmentDto EQUIPMENT_FOUR_HOUR_RESEARCH_TIME =
            EquipmentDto.builder()
                    .averageResearchTime(LocalTime.of(4,0))
                    .id(3L)
                    .name("THIRD Test Equipment")
                    .description("Equipment with 4 hour average research time")
                    .imageFilePath("/somewhere_else_else")
                    .laboratoryId(3L)
                    .pricePerHour(new BigDecimal("300.00"))
                    .build();
}
