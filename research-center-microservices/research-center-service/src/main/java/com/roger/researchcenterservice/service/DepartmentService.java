package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.DepartmentSaveDto;
import com.roger.researchcenterservice.dto.FullDepartmentDto;
import com.roger.researchcenterservice.dto.SlimDepartmentDto;

import java.util.List;

public interface DepartmentService {
    SlimDepartmentDto create(DepartmentSaveDto saveDto);
    List<SlimDepartmentDto> getAll();
    FullDepartmentDto getById(Long id);
    SlimDepartmentDto update(DepartmentSaveDto updateDto, Long id);

}
