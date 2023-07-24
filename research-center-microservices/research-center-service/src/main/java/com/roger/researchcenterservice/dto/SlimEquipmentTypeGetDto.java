package com.roger.researchcenterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlimEquipmentTypeGetDto implements DtoEntity{
    private String id;
    private String name;
    private String description;
}
