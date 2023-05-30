package com.roger.researchcenterservice.controller;

import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.EquipmentUpdateDto;
import com.roger.researchcenterservice.dto.ScheduleEquipmentGetDto;
import com.roger.researchcenterservice.service.EquipmentService;
import com.roger.researchcenterservice.service.impl.EquipmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping(value = "/equipment")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EquipmentGetDto createEquipment(@RequestBody EquipmentSaveDto equipmentDto){
        return equipmentService.create(equipmentDto);
    }
    @DeleteMapping(value = "/equipment/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        equipmentService.deleteById(id);
    }

    @GetMapping(value = "/equipment")
    public List<EquipmentGetDto> getAll(){
        return equipmentService.getAll();
    }

    @GetMapping(value = "/equipment/{id}")
    public EquipmentGetDto getById(@PathVariable Long id){
        return equipmentService.getById(id);
    }

    @GetMapping(value = "/equipment/{id}/for_schedule")
    public ScheduleEquipmentGetDto getByIdForSchedule(@PathVariable Long id){
        ScheduleEquipmentGetDto equipmentGetDto = equipmentService.getByIdForSchedule(id);
        System.out.println(equipmentGetDto);
        return equipmentGetDto;
    }


    @GetMapping(value = "/laboratories/{id}/equipment")
    public List<EquipmentGetDto> getEquipmentByLaboratoryId(@PathVariable Long id){
        return equipmentService.getByLaboratoryId(id);
    }
    @PutMapping(value = "/equipment/{id}")
    public EquipmentGetDto update(@RequestBody EquipmentUpdateDto updateDto, @PathVariable Long id){
        return equipmentService.update(updateDto, id);
    }

}
