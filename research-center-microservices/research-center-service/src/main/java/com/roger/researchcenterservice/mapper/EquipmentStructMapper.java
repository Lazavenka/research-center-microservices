package com.roger.researchcenterservice.mapper;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.researchcenterservice.dto.*;
import com.roger.researchcenterservice.model.Equipment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipmentStructMapper {

    EquipmentStructMapper INSTANCE = Mappers.getMapper(EquipmentStructMapper.class);

    @Mapping(target = "equipmentType", expression = "java(equipment.getEquipmentType().getName())")
    @Mapping(target = "laboratoryId", expression = "java(equipment.getLaboratory().getId())")
    EquipmentDto toEquipmentGetDto(Equipment equipment);
    List<EquipmentDto> toListEquipmentGetDto(List<Equipment> equipment);

    Equipment saveDtoToEntity(EquipmentSaveDto request);

}
