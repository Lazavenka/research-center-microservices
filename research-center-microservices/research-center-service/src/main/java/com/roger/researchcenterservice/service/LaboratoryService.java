package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.FullLaboratoryDto;
import com.roger.researchcenterservice.dto.LaboratorySaveDto;
import com.roger.researchcenterservice.dto.SlimLaboratoryDto;

import java.util.List;

public interface LaboratoryService {
    List<SlimLaboratoryDto> getAll();
    FullLaboratoryDto getById(Long id);
    List<SlimLaboratoryDto> getLaboratoriesByDepartmentId(Long departmentId);
    SlimLaboratoryDto create(LaboratorySaveDto laboratorySaveDto);
    SlimLaboratoryDto update(LaboratorySaveDto laboratorySaveDto, Long laboratoryId);
}
