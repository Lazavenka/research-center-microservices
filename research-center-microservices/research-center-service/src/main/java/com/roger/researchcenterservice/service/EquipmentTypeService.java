package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;

public interface EquipmentTypeService {
    SlimEquipmentTypeGetDto getById(Long id);
    FullEquipmentTypeDto getEquipmentByTypeId(Long id);
}
