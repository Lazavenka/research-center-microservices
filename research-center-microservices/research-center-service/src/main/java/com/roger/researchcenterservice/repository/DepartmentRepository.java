package com.roger.researchcenterservice.repository;

import com.roger.researchcenterservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
