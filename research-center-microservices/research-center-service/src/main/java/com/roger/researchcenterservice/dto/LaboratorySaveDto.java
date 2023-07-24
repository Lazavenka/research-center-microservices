package com.roger.researchcenterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboratorySaveDto implements DtoEntity{
    private String name;
    private String description;
    private String location;
    private long departmentId;
}
