package com.roger.researchcenterservice.service.impl;

import com.roger.researchcenterservice.dto.EquipmentTypeSaveDto;
import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;
import com.roger.researchcenterservice.exception.CustomNotFoundException;
import com.roger.researchcenterservice.mapper.EquipmentTypeStructMapper;
import com.roger.researchcenterservice.model.EquipmentType;
import com.roger.researchcenterservice.repository.EquipmentTypeRepository;
import com.roger.researchcenterservice.service.EquipmentTypeService;
import com.roger.researchcenterservice.service.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.validator.DtoFieldValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    private final EquipmentTypeRepository equipmentTypeRepository;
    private EquipmentTypeStructMapper mapper;
    private DtoFieldValidator<EquipmentTypeSaveDto> validator;

    @Override
    public SlimEquipmentTypeGetDto getById(Long id) {
        return mapper.entityToSlimGetDto(tryFindEquipmentTypeById(id));
    }

    @Override
    public FullEquipmentTypeDto getEquipmentByTypeId(Long id) {
        return mapper.entityToFullGetDto(tryFindEquipmentTypeById(id));
    }

    @Override
    public SlimEquipmentTypeGetDto create(EquipmentTypeSaveDto saveDto) {
        validator.validate(saveDto);
        EquipmentType equipmentType = mapper.saveDtoToEntity(saveDto);
        return mapper.entityToSlimGetDto(equipmentTypeRepository.saveAndFlush(equipmentType));
    }

    @Override
    public List<SlimEquipmentTypeGetDto> getAll() {
        return equipmentTypeRepository.findAll().stream()
                .map(mapper::entityToSlimGetDto)
                .toList();
    }

    private EquipmentType tryFindEquipmentTypeById(long equipmentTypeId) {
        validator.validateId(equipmentTypeId);
        Optional<EquipmentType> optionalEquipmentType = equipmentTypeRepository.findById(equipmentTypeId);
        if (optionalEquipmentType.isPresent()) {
            return optionalEquipmentType.get();
        } else {
            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_EQUIPMENT_TYPE_ID, equipmentTypeId);
        }
    }
}
