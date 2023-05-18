package com.roger.researchcenterservice.controller;

import com.roger.researchcenterservice.dto.EquipmentRequest;
import com.roger.researchcenterservice.dto.EquipmentResponse;
import com.roger.researchcenterservice.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@AllArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping
    public String createEquipment(@RequestBody EquipmentRequest equipmentDto){
        Long equipmentId = equipmentService.createEquipment(equipmentDto);
        return "Created successfully id = " + equipmentId;
    }

    @GetMapping
    public List<EquipmentResponse> getAll(){
        return equipmentService.getAllEquipment();
    }

    @GetMapping(value = "{id}")
    public EquipmentResponse getById(@PathVariable Long id){
        return equipmentService.getById(id);
    }
}
