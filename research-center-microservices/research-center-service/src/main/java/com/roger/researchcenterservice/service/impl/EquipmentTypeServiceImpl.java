package com.roger.researchcenterservice.service.impl;

import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;
import com.roger.researchcenterservice.mapper.EquipmentTypeStructMapper;
import com.roger.researchcenterservice.repository.EquipmentTypeRepository;
import com.roger.researchcenterservice.service.EquipmentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    private final EquipmentTypeRepository equipmentTypeRepository;

    @Override
    public SlimEquipmentTypeGetDto getById(Long id) {
        return EquipmentTypeStructMapper.INSTANCE
                .entityToSlimGetDto(equipmentTypeRepository.getReferenceById(id));
    }

    @Override
    public FullEquipmentTypeDto getEquipmentByTypeId(Long id) {
        return EquipmentTypeStructMapper.INSTANCE
                .entityToFullGetDto(equipmentTypeRepository.getReferenceById(id));
    }

    @Override
    public List<SlimEquipmentTypeGetDto> getAll() {
        return equipmentTypeRepository.findAll().stream()
                .map(EquipmentTypeStructMapper.INSTANCE::entityToSlimGetDto)
                .toList();
    }
}
