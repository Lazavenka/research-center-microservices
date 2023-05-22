package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.FullLaboratoryDto;
import com.roger.researchcenterservice.dto.LaboratorySaveDto;
import com.roger.researchcenterservice.dto.SlimLaboratoryDto;
import com.roger.researchcenterservice.model.Laboratory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = EquipmentStructMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LaboratoryStructMapper {

    LaboratoryStructMapper INSTANCE = Mappers.getMapper(LaboratoryStructMapper.class);

    SlimLaboratoryDto toSlimLaboratoryDto(Laboratory laboratory);
    FullLaboratoryDto toFullLaboratoryDto(Laboratory laboratory);
    List<SlimLaboratoryDto> toListSlimLaboratoryDto(List<Laboratory> laboratories);
    List<FullLaboratoryDto> toListFullLaboratoryDto(List<Laboratory> laboratories);
    Laboratory saveDtoToEntity(LaboratorySaveDto saveDto);
}
