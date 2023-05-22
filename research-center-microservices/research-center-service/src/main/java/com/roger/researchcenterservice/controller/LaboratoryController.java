package com.roger.researchcenterservice.controller;

import com.roger.researchcenterservice.dto.*;
import com.roger.researchcenterservice.service.LaboratoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
public class LaboratoryController {
    private final LaboratoryService laboratoryService;

    @GetMapping(value = "/laboratories")
    public List<SlimLaboratoryDto> getAll(){
        return laboratoryService.getAll();
    }

    @GetMapping(value = "/laboratories/{id}")
    public FullLaboratoryDto getById(@PathVariable Long id){
        return laboratoryService.getById(id);
    }

    @GetMapping(value = "/departments/{departmentId}/laboratories")
    public List<SlimLaboratoryDto> getLaboratoriesByDepartmentId(@PathVariable Long departmentId){
        return laboratoryService.getLaboratoriesByDepartmentId(departmentId);
    }

    @PostMapping(value = "/laboratories")
    public SlimLaboratoryDto createLaboratory(@RequestBody LaboratorySaveDto laboratorySaveDto){
        return laboratoryService.create(laboratorySaveDto);
    }

    @PutMapping(value = "/laboratories/{id}")
    public SlimLaboratoryDto update(@RequestBody LaboratorySaveDto updateDto, @PathVariable Long id){
        return laboratoryService.update(updateDto, id);
    }
}
