package com.roger.researchcenterservice.service.impl;

import com.roger.researchcenterservice.dto.DepartmentSaveDto;
import com.roger.researchcenterservice.dto.FullDepartmentDto;
import com.roger.researchcenterservice.dto.SlimDepartmentDto;
import com.roger.researchcenterservice.mapper.DepartmentStructMapper;
import com.roger.researchcenterservice.model.Department;
import com.roger.researchcenterservice.repository.DepartmentRepository;
import com.roger.researchcenterservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public SlimDepartmentDto create(DepartmentSaveDto saveDto) {

        DepartmentStructMapper mapper = DepartmentStructMapper.INSTANCE;
        Department department = mapper.saveDtoToEntity(saveDto);
        return mapper.entityToSlimDto(departmentRepository.saveAndFlush(department));

    }

    @Override
    public List<SlimDepartmentDto> getAll() {
        return DepartmentStructMapper.INSTANCE
                .toListSlimDto(departmentRepository.findAll());
    }

    @Override
    public FullDepartmentDto getById(Long id) {
        return DepartmentStructMapper.INSTANCE
                .toFullDepartmentDto(departmentRepository.getReferenceById(id));
    }

    @Override
    public SlimDepartmentDto update(DepartmentSaveDto updateDto, Long id) {
        Department department = departmentRepository.getReferenceById(id);
        department.setAddress(updateDto.getAddress());
        department.setDescription(updateDto.getDescription());
        department.setName(updateDto.getName());

        return DepartmentStructMapper.INSTANCE
                .entityToSlimDto(departmentRepository.saveAndFlush(department));
    }
}
