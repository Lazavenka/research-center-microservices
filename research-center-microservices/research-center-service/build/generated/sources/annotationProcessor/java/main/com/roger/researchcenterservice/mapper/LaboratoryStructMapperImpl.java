package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.FullLaboratoryDto;
import com.roger.researchcenterservice.dto.LaboratorySaveDto;
import com.roger.researchcenterservice.dto.SlimLaboratoryDto;
import com.roger.researchcenterservice.model.Laboratory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-30T22:12:05+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class LaboratoryStructMapperImpl implements LaboratoryStructMapper {

    private final EquipmentStructMapper equipmentStructMapper = EquipmentStructMapper.INSTANCE;

    @Override
    public SlimLaboratoryDto toSlimLaboratoryDto(Laboratory laboratory) {
        if ( laboratory == null ) {
            return null;
        }

        SlimLaboratoryDto.SlimLaboratoryDtoBuilder slimLaboratoryDto = SlimLaboratoryDto.builder();

        slimLaboratoryDto.id( laboratory.getId() );
        slimLaboratoryDto.name( laboratory.getName() );
        slimLaboratoryDto.description( laboratory.getDescription() );
        slimLaboratoryDto.location( laboratory.getLocation() );

        return slimLaboratoryDto.build();
    }

    @Override
    public FullLaboratoryDto toFullLaboratoryDto(Laboratory laboratory) {
        if ( laboratory == null ) {
            return null;
        }

        FullLaboratoryDto.FullLaboratoryDtoBuilder fullLaboratoryDto = FullLaboratoryDto.builder();

        fullLaboratoryDto.name( laboratory.getName() );
        fullLaboratoryDto.description( laboratory.getDescription() );
        fullLaboratoryDto.location( laboratory.getLocation() );
        fullLaboratoryDto.imageFilePath( laboratory.getImageFilePath() );
        fullLaboratoryDto.equipment( equipmentStructMapper.toListEquipmentGetDto( laboratory.getEquipment() ) );

        return fullLaboratoryDto.build();
    }

    @Override
    public List<SlimLaboratoryDto> toListSlimLaboratoryDto(List<Laboratory> laboratories) {
        if ( laboratories == null ) {
            return null;
        }

        List<SlimLaboratoryDto> list = new ArrayList<SlimLaboratoryDto>( laboratories.size() );
        for ( Laboratory laboratory : laboratories ) {
            list.add( toSlimLaboratoryDto( laboratory ) );
        }

        return list;
    }

    @Override
    public List<FullLaboratoryDto> toListFullLaboratoryDto(List<Laboratory> laboratories) {
        if ( laboratories == null ) {
            return null;
        }

        List<FullLaboratoryDto> list = new ArrayList<FullLaboratoryDto>( laboratories.size() );
        for ( Laboratory laboratory : laboratories ) {
            list.add( toFullLaboratoryDto( laboratory ) );
        }

        return list;
    }

    @Override
    public Laboratory saveDtoToEntity(LaboratorySaveDto saveDto) {
        if ( saveDto == null ) {
            return null;
        }

        Laboratory.LaboratoryBuilder laboratory = Laboratory.builder();

        laboratory.name( saveDto.getName() );
        laboratory.description( saveDto.getDescription() );
        laboratory.location( saveDto.getLocation() );

        return laboratory.build();
    }
}
