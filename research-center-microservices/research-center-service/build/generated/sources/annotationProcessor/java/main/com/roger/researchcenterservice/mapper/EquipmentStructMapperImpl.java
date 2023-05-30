package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.dto.ScheduleEquipmentGetDto;
import com.roger.researchcenterservice.dto.SlimEquipmentDto;
import com.roger.researchcenterservice.model.Equipment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-30T22:12:05+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class EquipmentStructMapperImpl implements EquipmentStructMapper {

    private final EquipmentTypeStructMapper equipmentTypeStructMapper = EquipmentTypeStructMapper.INSTANCE;
    private final LaboratoryStructMapper laboratoryStructMapper = LaboratoryStructMapper.INSTANCE;

    @Override
    public EquipmentGetDto toEquipmentGetDto(Equipment entity) {
        if ( entity == null ) {
            return null;
        }

        EquipmentGetDto.EquipmentGetDtoBuilder equipmentGetDto = EquipmentGetDto.builder();

        equipmentGetDto.slimLaboratoryDto( laboratoryStructMapper.toSlimLaboratoryDto( entity.getLaboratory() ) );
        equipmentGetDto.slimEquipmentTypeGetDto( equipmentTypeStructMapper.entityToSlimGetDto( entity.getEquipmentType() ) );
        equipmentGetDto.id( String.valueOf( entity.getId() ) );
        equipmentGetDto.name( entity.getName() );
        equipmentGetDto.description( entity.getDescription() );
        equipmentGetDto.imageFilePath( entity.getImageFilePath() );
        equipmentGetDto.state( entity.getState() );
        equipmentGetDto.pricePerHour( entity.getPricePerHour() );
        equipmentGetDto.averageResearchTime( entity.getAverageResearchTime() );

        return equipmentGetDto.build();
    }

    @Override
    public Equipment saveDtoToEntity(EquipmentSaveDto request) {
        if ( request == null ) {
            return null;
        }

        Equipment.EquipmentBuilder equipment = Equipment.builder();

        equipment.name( request.getName() );
        equipment.description( request.getDescription() );
        equipment.imageFilePath( request.getImageFilePath() );
        equipment.state( request.getState() );
        equipment.pricePerHour( request.getPricePerHour() );
        equipment.averageResearchTime( request.getAverageResearchTime() );

        return equipment.build();
    }

    @Override
    public SlimEquipmentDto entityToSlimEquipmentDto(Equipment equipment) {
        if ( equipment == null ) {
            return null;
        }

        SlimEquipmentDto.SlimEquipmentDtoBuilder slimEquipmentDto = SlimEquipmentDto.builder();

        slimEquipmentDto.slimLaboratoryDto( laboratoryStructMapper.toSlimLaboratoryDto( equipment.getLaboratory() ) );
        slimEquipmentDto.id( String.valueOf( equipment.getId() ) );
        slimEquipmentDto.name( equipment.getName() );
        slimEquipmentDto.description( equipment.getDescription() );
        slimEquipmentDto.imageFilePath( equipment.getImageFilePath() );
        slimEquipmentDto.state( equipment.getState() );
        slimEquipmentDto.pricePerHour( equipment.getPricePerHour() );
        slimEquipmentDto.averageResearchTime( equipment.getAverageResearchTime() );

        return slimEquipmentDto.build();
    }

    @Override
    public List<EquipmentGetDto> toListEquipmentGetDto(List<Equipment> equipment) {
        if ( equipment == null ) {
            return null;
        }

        List<EquipmentGetDto> list = new ArrayList<EquipmentGetDto>( equipment.size() );
        for ( Equipment equipment1 : equipment ) {
            list.add( toEquipmentGetDto( equipment1 ) );
        }

        return list;
    }

    @Override
    public List<SlimEquipmentDto> toListSlimEquipmentDto(List<Equipment> equipment) {
        if ( equipment == null ) {
            return null;
        }

        List<SlimEquipmentDto> list = new ArrayList<SlimEquipmentDto>( equipment.size() );
        for ( Equipment equipment1 : equipment ) {
            list.add( entityToSlimEquipmentDto( equipment1 ) );
        }

        return list;
    }

    @Override
    public ScheduleEquipmentGetDto entityToScheduleEquipmentDto(Equipment equipment) {
        if ( equipment == null ) {
            return null;
        }

        ScheduleEquipmentGetDto.ScheduleEquipmentGetDtoBuilder scheduleEquipmentGetDto = ScheduleEquipmentGetDto.builder();

        scheduleEquipmentGetDto.id( equipment.getId() );
        scheduleEquipmentGetDto.name( equipment.getName() );
        scheduleEquipmentGetDto.description( equipment.getDescription() );
        scheduleEquipmentGetDto.imageFilePath( equipment.getImageFilePath() );
        scheduleEquipmentGetDto.averageResearchTime( equipment.getAverageResearchTime() );

        scheduleEquipmentGetDto.laboratoryId( equipment.getLaboratory().getId() );

        return scheduleEquipmentGetDto.build();
    }
}
