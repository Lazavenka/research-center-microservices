package com.roger.researchcenterservice.repository;

import com.roger.researchcenterservice.model.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
    Optional<EquipmentType> findEquipmentTypeByName(String equipmentTypeName);
}
