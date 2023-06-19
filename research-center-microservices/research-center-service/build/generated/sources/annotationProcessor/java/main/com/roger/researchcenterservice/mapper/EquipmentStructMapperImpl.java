package com.roger.researchcenterservice.mapper;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.researchcenterservice.dto.EquipmentSaveDto;
import com.roger.researchcenterservice.model.Equipment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-19T13:23:36+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class EquipmentStructMapperImpl implements EquipmentStructMapper {

    @Override
    public EquipmentDto toEquipmentGetDto(Equipment equipment) {
        if ( equipment == null ) {
            return null;
        }

        EquipmentDto.EquipmentDtoBuilder equipmentDto = EquipmentDto.builder();

        equipmentDto.id( equipment.getId() );
        equipmentDto.name( equipment.getName() );
        equipmentDto.description( equipment.getDescription() );
        equipmentDto.imageFilePath( equipment.getImageFilePath() );
        equipmentDto.pricePerHour( equipment.getPricePerHour() );
        equipmentDto.averageResearchTime( equipment.getAverageResearchTime() );

        equipmentDto.equipmentType( equipment.getEquipmentType().getName() );
        equipmentDto.laboratoryId( equipment.getLaboratory().getId() );

        return equipmentDto.build();
    }

    @Override
    public List<EquipmentDto> toListEquipmentGetDto(List<Equipment> equipment) {
        if ( equipment == null ) {
            return null;
        }

        List<EquipmentDto> list = new ArrayList<EquipmentDto>( equipment.size() );
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
}
