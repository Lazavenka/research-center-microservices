package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.DepartmentSaveDto;
import com.roger.researchcenterservice.dto.FullDepartmentDto;
import com.roger.researchcenterservice.dto.SlimDepartmentDto;
import com.roger.researchcenterservice.model.Department;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = LaboratoryStructMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentStructMapper {
    DepartmentStructMapper INSTANCE = Mappers.getMapper(DepartmentStructMapper.class);

    SlimDepartmentDto entityToSlimDto(Department department);
    Department saveDtoToEntity(DepartmentSaveDto saveDto);
    List<SlimDepartmentDto> toListSlimDto(List<Department> departments);
    FullDepartmentDto toFullDepartmentDto(Department department);

}
