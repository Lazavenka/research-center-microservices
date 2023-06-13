package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.EquipmentInfoDto;
import com.roger.researchcenterservice.dto.EquipmentSaveDto;

import java.util.List;

public interface EquipmentService {
    EquipmentGetDto create(EquipmentSaveDto saveDto);
    EquipmentGetDto getById(Long id);
    List<EquipmentGetDto> getAll();
    EquipmentGetDto update(EquipmentSaveDto dtoEntity, Long id);
    void deleteById(Long id);
    List<EquipmentGetDto> getByLaboratoryId(Long laboratoryId);
    EquipmentInfoDto getByIdForInfo(Long id);
}
