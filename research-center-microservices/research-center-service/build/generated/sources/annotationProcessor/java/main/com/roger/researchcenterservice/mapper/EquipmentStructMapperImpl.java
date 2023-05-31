package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.EquipmentGetDto;
import com.roger.researchcenterservice.dto.EquipmentInfoDto;
import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.model.Equipment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T20:14:49+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class EquipmentStructMapperImpl implements EquipmentStructMapper {

    @Override
    public EquipmentGetDto toEquipmentGetDto(Equipment equipment) {
        if ( equipment == null ) {
            return null;
        }

        EquipmentGetDto.EquipmentGetDtoBuilder equipmentGetDto = EquipmentGetDto.builder();

        equipmentGetDto.id( equipment.getId() );
        equipmentGetDto.name( equipment.getName() );
        equipmentGetDto.description( equipment.getDescription() );
        equipmentGetDto.imageFilePath( equipment.getImageFilePath() );
        equipmentGetDto.state( equipment.getState() );
        equipmentGetDto.pricePerHour( equipment.getPricePerHour() );
        equipmentGetDto.averageResearchTime( equipment.getAverageResearchTime() );

        equipmentGetDto.equipmentType( equipment.getEquipmentType().getName() );
        equipmentGetDto.laboratoryId( equipment.getLaboratory().getId() );

        return equipmentGetDto.build();
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
    public EquipmentInfoDto entityToEquipmentInfoDto(Equipment equipment) {
        if ( equipment == null ) {
            return null;
        }

        EquipmentInfoDto.EquipmentInfoDtoBuilder equipmentInfoDto = EquipmentInfoDto.builder();

        equipmentInfoDto.id( equipment.getId() );
        equipmentInfoDto.name( equipment.getName() );
        equipmentInfoDto.description( equipment.getDescription() );
        equipmentInfoDto.imageFilePath( equipment.getImageFilePath() );
        equipmentInfoDto.pricePerHour( equipment.getPricePerHour() );
        equipmentInfoDto.averageResearchTime( equipment.getAverageResearchTime() );

        equipmentInfoDto.laboratoryId( equipment.getLaboratory().getId() );

        return equipmentInfoDto.build();
    }

    @Override
    public List<EquipmentInfoDto> toListEquipmentInfoDto(List<Equipment> equipment) {
        if ( equipment == null ) {
            return null;
        }

        List<EquipmentInfoDto> list = new ArrayList<EquipmentInfoDto>( equipment.size() );
        for ( Equipment equipment1 : equipment ) {
            list.add( entityToEquipmentInfoDto( equipment1 ) );
        }

        return list;
    }
}
