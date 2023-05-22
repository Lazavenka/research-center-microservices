package com.roger.researchcenterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullLaboratoryDto {
    private String name;
    private String description;
    private String location;
    private String imageFilePath;
    private List<EquipmentGetDto> equipment;
}
