package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.dto.EquipmentTypeSaveDto;
import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;

import java.util.List;

public interface EquipmentTypeService {
    SlimEquipmentTypeGetDto getById(Long id);
    FullEquipmentTypeDto getEquipmentByTypeId(Long id);
    SlimEquipmentTypeGetDto create(EquipmentTypeSaveDto saveDto);
    List<SlimEquipmentTypeGetDto> getAll();

}
