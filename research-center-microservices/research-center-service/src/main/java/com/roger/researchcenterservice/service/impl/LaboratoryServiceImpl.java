package com.roger.researchcenterservice.service.impl;

import com.roger.researchcenterservice.dto.FullLaboratoryDto;
import com.roger.researchcenterservice.dto.LaboratorySaveDto;
import com.roger.researchcenterservice.dto.SlimLaboratoryDto;
import com.roger.researchcenterservice.mapper.LaboratoryStructMapper;
import com.roger.researchcenterservice.model.Department;
import com.roger.researchcenterservice.model.Laboratory;
import com.roger.researchcenterservice.repository.DepartmentRepository;
import com.roger.researchcenterservice.repository.LaboratoryRepository;
import com.roger.researchcenterservice.service.LaboratoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LaboratoryServiceImpl implements LaboratoryService {

    private final LaboratoryRepository laboratoryRepository;
    private final DepartmentRepository departmentRepository;
    private LaboratoryStructMapper mapper;
    @Override
    public List<SlimLaboratoryDto> getAll() {
        return mapper.toListSlimLaboratoryDto(laboratoryRepository.findAll());
    }

    @Override
    public FullLaboratoryDto getById(Long id) {
        return mapper.toFullLaboratoryDto(laboratoryRepository.getReferenceById(id));
    }

    @Override
    public List<SlimLaboratoryDto> getLaboratoriesByDepartmentId(Long departmentId) {
        Department department = departmentRepository.getReferenceById(departmentId);
        return mapper.toListSlimLaboratoryDto(department.getLaboratories());
    }

    @Override
    public SlimLaboratoryDto create(LaboratorySaveDto laboratorySaveDto) {
        String laboratoryName = laboratorySaveDto.getName();
        Optional<Laboratory> optionalLaboratory = laboratoryRepository.findByName(laboratoryName);
        if (optionalLaboratory.isPresent()) {
            throw new RuntimeException("LABORATORY IS PRESENT!");
        }
        Laboratory laboratory = mapper.saveDtoToEntity(laboratorySaveDto);
        Department department = departmentRepository.getReferenceById(laboratorySaveDto.getDepartmentId());
        laboratory.setDepartment(department);

        return mapper.toSlimLaboratoryDto(laboratoryRepository.saveAndFlush(laboratory));
    }

    @Override
    public SlimLaboratoryDto update(LaboratorySaveDto laboratorySaveDto, Long laboratoryId) {
        Laboratory laboratory = laboratoryRepository.getReferenceById(laboratoryId);
        if (laboratory.getDepartment().getId() != laboratorySaveDto.getDepartmentId()) {
            Department newDepartment = departmentRepository.getReferenceById(laboratorySaveDto.getDepartmentId());
            laboratory.setDepartment(newDepartment);
        }

        laboratory.setName(laboratorySaveDto.getName());
        laboratory.setDescription(laboratorySaveDto.getDescription());
        laboratory.setLocation(laboratorySaveDto.getLocation());

        return mapper.toSlimLaboratoryDto(laboratoryRepository.saveAndFlush(laboratory));
    }
}
