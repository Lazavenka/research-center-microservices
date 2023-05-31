package com.roger.researchcenterservice.mapper;

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
    EquipmentGetDto toEquipmentGetDto(Equipment equipment);
    List<EquipmentGetDto> toListEquipmentGetDto(List<Equipment> equipment);

    Equipment saveDtoToEntity(EquipmentSaveDto request);

    @Mapping(target = "laboratoryId", expression = "java(equipment.getLaboratory().getId())")
    EquipmentInfoDto entityToEquipmentInfoDto(Equipment equipment);
    List<EquipmentInfoDto> toListEquipmentInfoDto(List<Equipment> equipment);

}
