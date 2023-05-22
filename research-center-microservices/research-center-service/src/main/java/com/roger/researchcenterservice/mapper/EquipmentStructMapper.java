package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.SlimEquipmentDto;
import com.roger.researchcenterservice.dto.SlimLaboratoryDto;
import com.roger.researchcenterservice.model.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {EquipmentTypeStructMapper.class,LaboratoryStructMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipmentStructMapper {

    EquipmentStructMapper INSTANCE = Mappers.getMapper(EquipmentStructMapper.class);

    @Mapping(source = "laboratory", target = "slimLaboratoryDto")
    @Mapping(source = "equipmentType", target = "slimEquipmentTypeGetDto")
    EquipmentGetDto toEquipmentGetDto(Equipment entity);
    Equipment saveDtoToEntity(EquipmentSaveDto request);
    @Mapping(source = "laboratory", target = "slimLaboratoryDto")
    SlimEquipmentDto entityToSlimEquipmentDto(Equipment equipment);
    List<EquipmentGetDto> toListEquipmentGetDto(List<Equipment> equipment);
    List<SlimEquipmentDto> toListSlimEquipmentDto(List<Equipment> equipment);

}
