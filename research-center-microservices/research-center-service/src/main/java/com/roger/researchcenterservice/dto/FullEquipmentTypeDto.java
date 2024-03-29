package com.roger.researchcenterservice.dto;

import com.roger.researchcenter.dto.EquipmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullEquipmentTypeDto implements DtoEntity{

    private String name;
    private String description;
    private List<EquipmentDto> equipment;

}
