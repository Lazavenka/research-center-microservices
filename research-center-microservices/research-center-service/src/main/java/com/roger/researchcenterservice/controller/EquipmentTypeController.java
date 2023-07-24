package com.roger.researchcenterservice.controller;

import com.roger.researchcenterservice.dto.EquipmentTypeSaveDto;
import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;
import com.roger.researchcenterservice.service.EquipmentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/equipment_types")
@AllArgsConstructor
public class EquipmentTypeController {

    private final EquipmentTypeService equipmentTypeService;

    @GetMapping(value = "/public")
    public List<SlimEquipmentTypeGetDto> getAll(){
        return equipmentTypeService.getAll();
    }


    @GetMapping(value = "/public/{id}")
    public SlimEquipmentTypeGetDto getById(@PathVariable Long id){
        return equipmentTypeService.getById(id);
    }

    @GetMapping(value = "/public/{id}/equipment")
    public FullEquipmentTypeDto getEquipmentByTypeId(@PathVariable Long id){
        return equipmentTypeService.getEquipmentByTypeId(id);
    }

    @PostMapping
    public SlimEquipmentTypeGetDto createEquipmentType(@RequestBody EquipmentTypeSaveDto equipmentTypeSaveDto){
        return equipmentTypeService.create(equipmentTypeSaveDto);
    }
}
