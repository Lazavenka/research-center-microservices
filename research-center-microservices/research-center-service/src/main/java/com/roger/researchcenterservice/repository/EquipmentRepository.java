package com.roger.researchcenterservice.repository;

import com.roger.researchcenterservice.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByName(String equipmentName);
}
