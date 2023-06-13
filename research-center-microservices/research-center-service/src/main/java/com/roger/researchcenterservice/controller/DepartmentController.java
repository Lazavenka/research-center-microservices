package com.roger.researchcenterservice.controller;

import com.roger.researchcenterservice.dto.DepartmentSaveDto;
import com.roger.researchcenterservice.dto.FullDepartmentDto;
import com.roger.researchcenterservice.dto.SlimDepartmentDto;
import com.roger.researchcenterservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<SlimDepartmentDto> getAll(){

        return departmentService.getAll();
    }

    @GetMapping(value = "{id}")
    public FullDepartmentDto getById(@PathVariable Long id){
        return departmentService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SlimDepartmentDto create(@RequestBody DepartmentSaveDto saveDto){
        return departmentService.create(saveDto);
    }
    @PutMapping(value = "{id}")
    public SlimDepartmentDto update(@RequestBody DepartmentSaveDto saveDto, @PathVariable Long id){
        return departmentService.update(saveDto, id);
    }
}
