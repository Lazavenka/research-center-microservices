package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;
import com.roger.researchcenterservice.mapper.EquipmentTypeStructMapper;
import com.roger.researchcenterservice.model.Equipment;
import com.roger.researchcenterservice.model.EquipmentType;
import com.roger.researchcenterservice.repository.EquipmentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipmentTypeService {
    private final EquipmentTypeRepository equipmentTypeRepository;


    public SlimEquipmentTypeGetDto getById(Long id) {
        return EquipmentTypeStructMapper.INSTANCE
                .entityToSlimGetDto(equipmentTypeRepository.getReferenceById(id));
    }

    public FullEquipmentTypeDto getEquipmentByTypeId(Long id) {
        return EquipmentTypeStructMapper.INSTANCE
                .entityToFullGetDto(equipmentTypeRepository.getReferenceById(id));
    }
}
