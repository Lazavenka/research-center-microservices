package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.EquipmentInfoDto;
import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.dto.EquipmentUpdateDto;

import java.util.List;

public interface EquipmentService {
    EquipmentGetDto create(EquipmentSaveDto saveDto);
    EquipmentGetDto getById(Long id);
    List<EquipmentGetDto> getAll();
    EquipmentGetDto update(EquipmentUpdateDto dtoEntity, Long id);
    void deleteById(Long id);
    List<EquipmentGetDto> getByLaboratoryId(Long laboratoryId);
    EquipmentInfoDto getByIdForInfo(Long id);
}
