package com.roger.researchcenterservice.service;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.researchcenterservice.dto.EquipmentSaveDto;

import java.util.List;

public interface EquipmentService {
    EquipmentDto create(EquipmentSaveDto saveDto);
    EquipmentDto getById(Long id);
    List<EquipmentDto> getAll();
    EquipmentDto update(EquipmentSaveDto dtoEntity, Long id);
    void deleteById(Long id);
    List<EquipmentDto> getByLaboratoryId(Long laboratoryId);
    EquipmentDto getByIdForInfo(Long id);
}
