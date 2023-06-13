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
public class FullDepartmentDto implements DtoEntity{
    private long id;
    private String name;
    private String description;
    private String address;
    private List<SlimLaboratoryDto> laboratories;
}
