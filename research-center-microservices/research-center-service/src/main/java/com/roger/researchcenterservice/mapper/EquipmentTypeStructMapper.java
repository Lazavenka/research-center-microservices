package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.EquipmentTypeSaveDto;
import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;
import com.roger.researchcenterservice.model.EquipmentType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EquipmentStructMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipmentTypeStructMapper {

    EquipmentTypeStructMapper INSTANCE = Mappers.getMapper(EquipmentTypeStructMapper.class);

    SlimEquipmentTypeGetDto entityToSlimGetDto(EquipmentType equipmentType);
    EquipmentType saveDtoToEntity(EquipmentTypeSaveDto equipmentType);

    FullEquipmentTypeDto entityToFullGetDto(EquipmentType equipmentType);

}
