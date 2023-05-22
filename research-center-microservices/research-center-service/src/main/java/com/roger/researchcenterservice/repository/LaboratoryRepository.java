package com.roger.researchcenterservice.repository;

import com.roger.researchcenterservice.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
    Optional<Laboratory> findByName(String laboratoryName);
}
