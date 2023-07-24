package com.roger.researchcenterservice.service.impl;

import com.roger.researchcenter.exception.CustomNotFoundException;
import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.dto.FullLaboratoryDto;
import com.roger.researchcenterservice.dto.LaboratorySaveDto;
import com.roger.researchcenterservice.dto.SlimLaboratoryDto;
import com.roger.researchcenterservice.mapper.LaboratoryStructMapper;
import com.roger.researchcenterservice.model.Department;
import com.roger.researchcenterservice.model.Laboratory;
import com.roger.researchcenterservice.repository.DepartmentRepository;
import com.roger.researchcenterservice.repository.LaboratoryRepository;
import com.roger.researchcenterservice.service.LaboratoryService;
import com.roger.researchcenterservice.validator.DtoFieldValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LaboratoryServiceImpl implements LaboratoryService {

    private final LaboratoryRepository laboratoryRepository;
    private final DepartmentRepository departmentRepository;
    private LaboratoryStructMapper mapper;
    private DtoFieldValidator<LaboratorySaveDto> validator;

    @Override
    public List<SlimLaboratoryDto> getAll() {
        return mapper.toListSlimLaboratoryDto(laboratoryRepository.findAll());
    }

    @Override
    public FullLaboratoryDto getById(Long id) {
        return mapper.toFullLaboratoryDto(tryFindLaboratoryById(id));
    }

    @Override
    public List<SlimLaboratoryDto> getLaboratoriesByDepartmentId(Long departmentId) {
        Department department = tryFindDepartmentById(departmentId);
        return mapper.toListSlimLaboratoryDto(department.getLaboratories());
    }
    @Transactional
    @Override
    public SlimLaboratoryDto create(LaboratorySaveDto laboratorySaveDto) {
        validator.validate(laboratorySaveDto);
        String laboratoryName = laboratorySaveDto.getName();
        Optional<Laboratory> optionalLaboratory = laboratoryRepository.findByName(laboratoryName);
        if (optionalLaboratory.isPresent()) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.LABORATORY_EXISTS);
        }
        Laboratory laboratory = mapper.saveDtoToEntity(laboratorySaveDto);
        Department department = tryFindDepartmentById(laboratorySaveDto.getDepartmentId());
        laboratory.setDepartment(department);

        return mapper.toSlimLaboratoryDto(laboratoryRepository.saveAndFlush(laboratory));
    }
    @Transactional
    @Override
    public SlimLaboratoryDto update(LaboratorySaveDto laboratorySaveDto, Long laboratoryId) {
        validator.validate(laboratorySaveDto);
        Laboratory laboratory = tryFindLaboratoryById(laboratoryId);
        if (laboratory.getDepartment().getId() != laboratorySaveDto.getDepartmentId()) {
            Department newDepartment = tryFindDepartmentById(laboratorySaveDto.getDepartmentId());
            laboratory.setDepartment(newDepartment);
        }

        laboratory.setName(laboratorySaveDto.getName());
        laboratory.setDescription(laboratorySaveDto.getDescription());
        laboratory.setLocation(laboratorySaveDto.getLocation());

        return mapper.toSlimLaboratoryDto(laboratoryRepository.saveAndFlush(laboratory));
    }

    private Laboratory tryFindLaboratoryById(Long laboratoryId) {
        validator.validateId(laboratoryId);
        Optional<Laboratory> optionalLaboratory = laboratoryRepository.findById(laboratoryId);
        if (optionalLaboratory.isPresent()) {
            return optionalLaboratory.get();
        } else {
            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_LABORATORY_ID, laboratoryId);
        }
    }
    private Department tryFindDepartmentById(Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            return optionalDepartment.get();
        } else {
            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_DEPARTMENT_ID, departmentId);
        }
    }
}
