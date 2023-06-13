package com.roger.researchcenterservice.service.impl;

import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.EquipmentInfoDto;
import com.roger.researchcenterservice.exception.CustomNotFoundException;
import com.roger.researchcenterservice.exception.IncorrectRequestException;
import com.roger.researchcenterservice.mapper.EquipmentStructMapper;
import com.roger.researchcenterservice.model.Equipment;
import com.roger.researchcenterservice.model.EquipmentType;
import com.roger.researchcenterservice.model.Laboratory;
import com.roger.researchcenterservice.repository.EquipmentRepository;
import com.roger.researchcenterservice.repository.EquipmentTypeRepository;
import com.roger.researchcenterservice.repository.LaboratoryRepository;
import com.roger.researchcenterservice.service.EquipmentService;
import com.roger.researchcenterservice.service.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.validator.DtoFieldValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final DtoFieldValidator<EquipmentSaveDto> validator;
    private final EquipmentStructMapper mapper;

    @Override
    public EquipmentGetDto create(EquipmentSaveDto saveDto) {
        validator.validate(saveDto);
        String equipmentName = saveDto.getName();
        Optional<Equipment> optionalEquipment = equipmentRepository.findByName(equipmentName);
        if (optionalEquipment.isPresent()) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.EQUIPMENT_EXISTS);
        }
        Equipment equipment = mapper.saveDtoToEntity(saveDto);
        Laboratory laboratory = tryFindLaboratoryById(saveDto.getLaboratoryId());
        equipment.setLaboratory(laboratory);
        EquipmentType equipmentType = tryFindEquipmentTypeById(saveDto.getEquipmentTypeId());
        equipment.setEquipmentType(equipmentType);
        return mapper.toEquipmentGetDto(equipmentRepository.saveAndFlush(equipment));
    }

    @Override
    public EquipmentGetDto getById(Long id) {
        return mapper.toEquipmentGetDto(tryFindEquipmentById(id));
    }

    @Override
    public List<EquipmentGetDto> getAll() {
        return mapper.toListEquipmentGetDto(equipmentRepository.findAll());
    }

    @Override
    public EquipmentGetDto update(EquipmentSaveDto dtoEntity, Long id) {
        Equipment equipment = tryFindEquipmentById(id);
        validator.validate(dtoEntity);
        if (equipment.getLaboratory().getId() != dtoEntity.getLaboratoryId()) {
            Laboratory newLaboratory = tryFindLaboratoryById(dtoEntity.getLaboratoryId());
            equipment.setLaboratory(newLaboratory);
        }
        if (equipment.getEquipmentType().getId() != dtoEntity.getEquipmentTypeId()) {
            EquipmentType newEquipmentType = tryFindEquipmentTypeById(dtoEntity.getEquipmentTypeId());
            equipment.setEquipmentType(newEquipmentType);
        }
        equipment.setName(dtoEntity.getName());
        equipment.setDescription(dtoEntity.getDescription());
        equipment.setAverageResearchTime(dtoEntity.getAverageResearchTime());
        equipment.setPricePerHour(dtoEntity.getPricePerHour());
        equipment.setState(dtoEntity.getState());

        return mapper.toEquipmentGetDto(equipmentRepository.saveAndFlush(equipment));
    }

    @Override
    public void deleteById(Long id) {
        validator.validateId(id);
        equipmentRepository.deleteById(id);
    }

    @Override
    public List<EquipmentGetDto> getByLaboratoryId(Long laboratoryId) {
        validator.validateId(laboratoryId);
        return mapper.toListEquipmentGetDto(equipmentRepository.getEquipmentByLaboratoryId(laboratoryId));
    }

    @Override
    public EquipmentInfoDto getByIdForInfo(Long id) {
        return mapper.entityToEquipmentInfoDto(tryFindEquipmentById(id));
    }

    private Equipment tryFindEquipmentById(Long equipmentId) {
        validator.validateId(equipmentId);
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(equipmentId);
        if (optionalEquipment.isPresent()) {
            return optionalEquipment.get();
        } else {
            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_EQUIPMENT_ID, equipmentId);
        }
    }

    private Laboratory tryFindLaboratoryById(long laboratoryId) {
        Optional<Laboratory> optionalLaboratory = laboratoryRepository.findById(laboratoryId);
        if (optionalLaboratory.isPresent()) {
            return optionalLaboratory.get();
        } else {
            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_LABORATORY_ID, laboratoryId);
        }
    }

    private EquipmentType tryFindEquipmentTypeById(long equipmentTypeId) {
        Optional<EquipmentType> optionalEquipmentType = equipmentTypeRepository.findById(equipmentTypeId);
        if (optionalEquipmentType.isPresent()) {
            return optionalEquipmentType.get();
        } else {
            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_LABORATORY_ID, equipmentTypeId);
        }
    }
}
