package com.roger.researchcenterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlimDepartmentDto implements DtoEntity{
    private long id;
    private String name;
    private String description;
    private String address;
}
