package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.EquipmentTypeSaveDto;
import com.roger.researchcenterservice.dto.FullEquipmentTypeDto;
import com.roger.researchcenterservice.dto.SlimEquipmentTypeGetDto;
import com.roger.researchcenterservice.model.EquipmentType;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-21T14:38:36+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class EquipmentTypeStructMapperImpl implements EquipmentTypeStructMapper {

    private final EquipmentStructMapper equipmentStructMapper;

    @Autowired
    public EquipmentTypeStructMapperImpl(EquipmentStructMapper equipmentStructMapper) {

        this.equipmentStructMapper = equipmentStructMapper;
    }

    @Override
    public SlimEquipmentTypeGetDto entityToSlimGetDto(EquipmentType equipmentType) {
        if ( equipmentType == null ) {
            return null;
        }

        SlimEquipmentTypeGetDto.SlimEquipmentTypeGetDtoBuilder slimEquipmentTypeGetDto = SlimEquipmentTypeGetDto.builder();

        slimEquipmentTypeGetDto.id( String.valueOf( equipmentType.getId() ) );
        slimEquipmentTypeGetDto.name( equipmentType.getName() );
        slimEquipmentTypeGetDto.description( equipmentType.getDescription() );

        return slimEquipmentTypeGetDto.build();
    }

    @Override
    public EquipmentType saveDtoToEntity(EquipmentTypeSaveDto equipmentType) {
        if ( equipmentType == null ) {
            return null;
        }

        EquipmentType.EquipmentTypeBuilder equipmentType1 = EquipmentType.builder();

        equipmentType1.name( equipmentType.getName() );
        equipmentType1.description( equipmentType.getDescription() );

        return equipmentType1.build();
    }

    @Override
    public FullEquipmentTypeDto entityToFullGetDto(EquipmentType equipmentType) {
        if ( equipmentType == null ) {
            return null;
        }

        FullEquipmentTypeDto.FullEquipmentTypeDtoBuilder fullEquipmentTypeDto = FullEquipmentTypeDto.builder();

        fullEquipmentTypeDto.name( equipmentType.getName() );
        fullEquipmentTypeDto.description( equipmentType.getDescription() );
        fullEquipmentTypeDto.equipment( equipmentStructMapper.toListEquipmentGetDto( equipmentType.getEquipment() ) );

        return fullEquipmentTypeDto.build();
    }
}
