package com.roger.researchcenterservice.dto;

import com.roger.researchcenterservice.model.EquipmentState;
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
public class EquipmentSaveDto implements DtoEntity{
    private String name;
    private String description;
    private String imageFilePath;
    private long equipmentTypeId;
    private long laboratoryId;
    private EquipmentState state;
    private BigDecimal pricePerHour;
    private LocalTime averageResearchTime;
}
