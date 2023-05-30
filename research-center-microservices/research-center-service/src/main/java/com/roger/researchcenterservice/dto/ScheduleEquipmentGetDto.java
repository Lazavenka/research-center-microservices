package com.roger.researchcenterservice.dto;

import com.roger.researchcenterservice.model.EquipmentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleEquipmentGetDto {
    private Long id;
    private String name;
    private String description;
    private String imageFilePath;
    private Long laboratoryId;
    private LocalTime averageResearchTime;
}
