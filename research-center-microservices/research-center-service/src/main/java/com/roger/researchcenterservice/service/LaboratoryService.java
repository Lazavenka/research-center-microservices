package com.roger.researchcenterservice.service;

import com.roger.researchcenterservice.dto.FullLaboratoryDto;
import com.roger.researchcenterservice.dto.LaboratorySaveDto;
import com.roger.researchcenterservice.dto.SlimLaboratoryDto;
import com.roger.researchcenterservice.mapper.LaboratoryStructMapper;
import com.roger.researchcenterservice.model.Department;
import com.roger.researchcenterservice.model.Laboratory;
import com.roger.researchcenterservice.repository.DepartmentRepository;
import com.roger.researchcenterservice.repository.LaboratoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LaboratoryService {

    private final LaboratoryRepository laboratoryRepository;
    private final DepartmentRepository departmentRepository;

    public List<SlimLaboratoryDto> getAll() {
        return LaboratoryStructMapper.INSTANCE
                .toListSlimLaboratoryDto(laboratoryRepository.findAll());
    }

    public FullLaboratoryDto getById(Long id) {
        return LaboratoryStructMapper.INSTANCE
                .toFullLaboratoryDto(laboratoryRepository.getReferenceById(id));
    }

    public List<SlimLaboratoryDto> getLaboratoriesByDepartmentId(Long departmentId) {
        Department department = departmentRepository.getReferenceById(departmentId);
        return LaboratoryStructMapper.INSTANCE
                .toListSlimLaboratoryDto(department.getLaboratories());
    }

    public SlimLaboratoryDto create(LaboratorySaveDto laboratorySaveDto) {
        String laboratoryName = laboratorySaveDto.getName();
        Optional<Laboratory> optionalLaboratory = laboratoryRepository.findByName(laboratoryName);
        if (optionalLaboratory.isPresent()){
            throw new RuntimeException("LABORATORY IS PRESENT!");
        }
        LaboratoryStructMapper mapper = LaboratoryStructMapper.INSTANCE;
        Laboratory laboratory = mapper.saveDtoToEntity(laboratorySaveDto);
        Department department = departmentRepository.getReferenceById(laboratorySaveDto.getDepartmentId());
        laboratory.setDepartment(department);

        return mapper.toSlimLaboratoryDto(laboratoryRepository.saveAndFlush(laboratory));
    }

    public SlimLaboratoryDto update(LaboratorySaveDto laboratorySaveDto, Long laboratoryId){
        Laboratory laboratory = laboratoryRepository.getReferenceById(laboratoryId);
        if(laboratory.getDepartment().getId() != laboratorySaveDto.getDepartmentId()){
            Department newDepartment = departmentRepository.getReferenceById(laboratorySaveDto.getDepartmentId());
            laboratory.setDepartment(newDepartment);
        }

        laboratory.setName(laboratorySaveDto.getName());
        laboratory.setDescription(laboratorySaveDto.getDescription());
        laboratory.setLocation(laboratorySaveDto.getLocation());

        return LaboratoryStructMapper.INSTANCE
                .toSlimLaboratoryDto(laboratoryRepository.saveAndFlush(laboratory));
    }
}
