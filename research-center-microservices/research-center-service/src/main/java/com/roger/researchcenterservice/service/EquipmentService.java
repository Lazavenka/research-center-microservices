package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.EquipmentRequest;
import com.roger.researchcenterservice.dto.EquipmentResponse;
import com.roger.researchcenterservice.model.Equipment;
import com.roger.researchcenterservice.repository.EquipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public Long createEquipment(EquipmentRequest equipmentRequest){
        String equipmentName = equipmentRequest.getName();
        Optional<Equipment> optionalEquipment = equipmentRepository.findByName(equipmentName);
        if (optionalEquipment.isPresent()){
            throw new RuntimeException("EQUIPMENT IS PRESENT"); //todo make custom exception
        }
        Equipment equipment = requestToEntity(equipmentRequest);
        return equipmentRepository.saveAndFlush(equipment).getId();
    }

    public List<EquipmentResponse> getAllEquipment() {
        return equipmentRepository.findAll().stream().map(this::entityToResponse).toList();
    }

    public EquipmentResponse getById(Long id) {
        return entityToResponse(equipmentRepository.getReferenceById(id));
    }

    private EquipmentResponse entityToResponse(Equipment entity){
        return EquipmentResponse.builder()
                .id(String.valueOf(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .imageFilePath(entity.getImageFilePath())
                .equipmentTypeId(entity.getEquipmentTypeId())
                .laboratoryId(entity.getLaboratoryId())
                .isNeedAssistant(entity.isNeedAssistant())
                .averageResearchTime(entity.getAverageResearchTime())
                .pricePerHour(entity.getPricePerHour())
                .build();
    }
    private Equipment requestToEntity(EquipmentRequest request){
        return Equipment.builder()
                .name(request.getName())
                .description(request.getDescription())
                .equipmentTypeId(request.getEquipmentTypeId())
                .laboratoryId(request.getLaboratoryId())
                .isNeedAssistant(request.isNeedAssistant())
                .state(request.getState())
                .pricePerHour(request.getPricePerHour())
                .averageResearchTime(request.getAverageResearchTime())
                .build();
    }
}
