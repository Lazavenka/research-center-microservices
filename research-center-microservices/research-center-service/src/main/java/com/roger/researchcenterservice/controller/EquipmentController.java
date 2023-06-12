package com.roger.researchcenterservice.controller;

import com.roger.researchcenterservice.dto.EquipmentInfoDto;
import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.EquipmentUpdateDto;
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
    public EquipmentGetDto createEquipment(@RequestBody EquipmentSaveDto equipmentDto){
        return equipmentService.create(equipmentDto);
    }
    @DeleteMapping(value = "/equipment/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        equipmentService.deleteById(id);
    }

    @GetMapping(value = "/equipment")
    public ResponseEntity<List<EquipmentGetDto>> getAll(){
        List<EquipmentGetDto> equipmentGetDtoList = equipmentService.getAll();
        if(equipmentGetDtoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(equipmentGetDtoList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(equipmentGetDtoList);

    }

    @GetMapping(value = "/equipment/{id}")
    public EquipmentGetDto getById(@PathVariable Long id){
        return equipmentService.getById(id);
    }

    @GetMapping(value = "/equipment/{id}/info")
    public EquipmentInfoDto getByIdForSchedule(@PathVariable Long id){
        return equipmentService.getByIdForInfo(id);
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
