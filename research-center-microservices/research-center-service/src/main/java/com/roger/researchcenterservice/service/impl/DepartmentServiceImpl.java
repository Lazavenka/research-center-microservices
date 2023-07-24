package com.roger.researchcenterservice.service.impl;

import com.roger.researchcenter.exception.CustomNotFoundException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.researchcenterservice.dto.DepartmentSaveDto;
import com.roger.researchcenterservice.dto.FullDepartmentDto;
import com.roger.researchcenterservice.dto.SlimDepartmentDto;
import com.roger.researchcenterservice.mapper.DepartmentStructMapper;
import com.roger.researchcenterservice.model.Department;
import com.roger.researchcenterservice.repository.DepartmentRepository;
import com.roger.researchcenterservice.service.DepartmentService;
import com.roger.researchcenterservice.validator.DtoFieldValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentStructMapper mapper;
    private DtoFieldValidator<DepartmentSaveDto> validator;

    @Transactional
    @Override
    public SlimDepartmentDto create(DepartmentSaveDto saveDto) {
        validator.validate(saveDto);
        Department department = mapper.saveDtoToEntity(saveDto);
        return mapper.entityToSlimDto(departmentRepository.saveAndFlush(department));

    }

    @Override
    public List<SlimDepartmentDto> getAll() {
        return mapper.toListSlimDto(departmentRepository.findAll());
    }

    @Override
    public FullDepartmentDto getById(Long id) {
        validator.validateId(id);
        return mapper.toFullDepartmentDto(tryFindDepartmentById(id));
    }
    @Transactional
    @Override
    public SlimDepartmentDto update(DepartmentSaveDto updateDto, Long id) {
        validator.validateId(id);
        Department department = tryFindDepartmentById(id);

        department.setAddress(updateDto.getAddress());
        department.setDescription(updateDto.getDescription());
        department.setName(updateDto.getName());

        return mapper.entityToSlimDto(departmentRepository.saveAndFlush(department));
    }

    private Department tryFindDepartmentById(Long id) {
        validator.validateId(id);
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            return optionalDepartment.get();
        } else {
            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_DEPARTMENT_ID, id);
        }
    }
}
