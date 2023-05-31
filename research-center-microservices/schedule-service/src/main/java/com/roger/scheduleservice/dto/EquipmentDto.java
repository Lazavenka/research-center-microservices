package com.roger.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentDto {
    private Long id;
    private String name;
    private String description;
    private String imageFilePath;
    private Long laboratoryId;
    private BigDecimal pricePerHour;
    private LocalTime averageResearchTime;
}
