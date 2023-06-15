package com.roger.researchcenterservice.controller;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping(value = "/equipment")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EquipmentDto createEquipment(@RequestBody EquipmentSaveDto equipmentDto){
        return equipmentService.create(equipmentDto);
    }
    @DeleteMapping(value = "/equipment/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        equipmentService.deleteById(id);
    }

    @GetMapping(value = "/equipment")
    public ResponseEntity<List<EquipmentDto>> getAll(){
        List<EquipmentDto> equipmentGetDtoList = equipmentService.getAll();
        if(equipmentGetDtoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(equipmentGetDtoList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(equipmentGetDtoList);

    }

    @GetMapping(value = "/equipment/{id}")
    public EquipmentDto getById(@PathVariable Long id){
        return equipmentService.getById(id);
    }

    @GetMapping(value = "/equipment/{id}/info")
    public EquipmentDto getByIdForSchedule(@PathVariable Long id){
        return equipmentService.getByIdForInfo(id);
    }

    @GetMapping(value = "/laboratories/{id}/equipment")
    public List<EquipmentDto> getEquipmentByLaboratoryId(@PathVariable Long id){
        return equipmentService.getByLaboratoryId(id);
    }
    @PutMapping(value = "/equipment/{id}")
    public EquipmentDto update(@RequestBody EquipmentSaveDto updateDto, @PathVariable Long id){
        return equipmentService.update(updateDto, id);
    }

}
