package com.roger.researchcenterservice.repository;

import com.roger.researchcenterservice.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository  extends JpaRepository<Equipment, Long>{
    Optional<Equipment> findByName(String equipmentName);
    List<Equipment> getEquipmentByLaboratoryId(Long id);

}
