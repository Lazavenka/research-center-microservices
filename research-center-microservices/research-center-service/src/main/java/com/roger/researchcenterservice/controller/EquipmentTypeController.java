package com.roger.researchcenterservice.controller;

import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;
import com.roger.researchcenterservice.service.EquipmentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/equipment_types")
@AllArgsConstructor
public class EquipmentTypeController {

    private final EquipmentTypeService equipmentTypeService;

    @GetMapping(value = "/{id}")
    public SlimEquipmentTypeGetDto getById(@PathVariable Long id){
        return equipmentTypeService.getById(id);
    }

    @GetMapping(value = "/{id}/equipment")
    public FullEquipmentTypeDto getEquipmentByTypeId(@PathVariable Long id){
        return equipmentTypeService.getEquipmentByTypeId(id);
    }
}
