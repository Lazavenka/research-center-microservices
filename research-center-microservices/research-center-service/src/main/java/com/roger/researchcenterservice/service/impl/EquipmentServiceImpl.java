package com.roger.researchcenterservice.service.impl;

import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.EquipmentUpdateDto;
import com.roger.researchcenterservice.dto.ScheduleEquipmentGetDto;
import com.roger.researchcenterservice.mapper.EquipmentStructMapper;
import com.roger.researchcenterservice.mapper.EquipmentStructMapperImpl;
import com.roger.researchcenterservice.model.Equipment;
import com.roger.researchcenterservice.model.EquipmentType;
import com.roger.researchcenterservice.model.Laboratory;
import com.roger.researchcenterservice.repository.EquipmentRepository;
import com.roger.researchcenterservice.repository.EquipmentTypeRepository;
import com.roger.researchcenterservice.repository.LaboratoryRepository;
import com.roger.researchcenterservice.service.EquipmentService;
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

    @Override
    public EquipmentGetDto create(EquipmentSaveDto saveDto) {
        String equipmentName = saveDto.getName();
        Optional<Equipment> optionalEquipment = equipmentRepository.findByName(equipmentName);
        if (optionalEquipment.isPresent()) {
            throw new RuntimeException("EQUIPMENT IS PRESENT"); //todo make custom exception
        }
        EquipmentStructMapper mapper = EquipmentStructMapper.INSTANCE;
        Equipment equipment = mapper.saveDtoToEntity(saveDto);
        Laboratory laboratory = laboratoryRepository.getReferenceById(saveDto.getLaboratoryId());
        equipment.setLaboratory(laboratory);
        EquipmentType equipmentType = equipmentTypeRepository.getReferenceById(saveDto.getEquipmentTypeId());
        equipment.setEquipmentType(equipmentType);
        return mapper.toEquipmentGetDto(equipmentRepository.saveAndFlush(equipment));
    }

    @Override
    public EquipmentGetDto getById(Long id) {
        return EquipmentStructMapper.INSTANCE
                .toEquipmentGetDto(equipmentRepository.getReferenceById(id));
    }

    @Override
    public List<EquipmentGetDto> getAll() {
        return EquipmentStructMapper.INSTANCE
                .toListEquipmentGetDto(equipmentRepository.findAll());
    }

    @Override
    public EquipmentGetDto update(EquipmentUpdateDto dtoEntity, Long id) {
        Equipment equipment = equipmentRepository.getReferenceById(id);
        if (equipment.getLaboratory().getId() != dtoEntity.getLaboratoryId()) {
            Laboratory newLaboratory = laboratoryRepository.getReferenceById(dtoEntity.getLaboratoryId());
            equipment.setLaboratory(newLaboratory);
        }
        if (equipment.getEquipmentType().getId() != dtoEntity.getEquipmentTypeId()) {
            EquipmentType newEquipmentType = equipmentTypeRepository.getReferenceById(dtoEntity.getEquipmentTypeId());
            equipment.setEquipmentType(newEquipmentType);
        }
        equipment.setName(dtoEntity.getName());
        equipment.setDescription(dtoEntity.getDescription());
        equipment.setAverageResearchTime(dtoEntity.getAverageResearchTime());
        equipment.setPricePerHour(dtoEntity.getPricePerHour());
        equipment.setState(dtoEntity.getState());

        return EquipmentStructMapper.INSTANCE
                .toEquipmentGetDto(equipmentRepository.saveAndFlush(equipment));
    }

    @Override
    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

    @Override
    public List<EquipmentGetDto> getByLaboratoryId(Long laboratoryId) {
        return EquipmentStructMapper.INSTANCE
                .toListEquipmentGetDto(equipmentRepository.getEquipmentByLaboratoryId(laboratoryId));
    }

    @Override
    public ScheduleEquipmentGetDto getByIdForSchedule(Long id) {
        return EquipmentStructMapperImpl.INSTANCE
                .entityToScheduleEquipmentDto(equipmentRepository.getReferenceById(id));
    }

}
