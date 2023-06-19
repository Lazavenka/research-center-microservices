package com.roger.researchcenterservice.mapper;

import com.roger.researchcenterservice.dto.DepartmentSaveDto;
import com.roger.researchcenterservice.dto.FullDepartmentDto;
import com.roger.researchcenterservice.dto.SlimDepartmentDto;
import com.roger.researchcenterservice.model.Department;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-19T13:23:36+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class DepartmentStructMapperImpl implements DepartmentStructMapper {

    private final LaboratoryStructMapper laboratoryStructMapper;

    @Autowired
    public DepartmentStructMapperImpl(LaboratoryStructMapper laboratoryStructMapper) {

        this.laboratoryStructMapper = laboratoryStructMapper;
    }

    @Override
    public SlimDepartmentDto entityToSlimDto(Department department) {
        if ( department == null ) {
            return null;
        }

        SlimDepartmentDto.SlimDepartmentDtoBuilder slimDepartmentDto = SlimDepartmentDto.builder();

        slimDepartmentDto.id( department.getId() );
        slimDepartmentDto.name( department.getName() );
        slimDepartmentDto.description( department.getDescription() );
        slimDepartmentDto.address( department.getAddress() );

        return slimDepartmentDto.build();
    }

    @Override
    public Department saveDtoToEntity(DepartmentSaveDto saveDto) {
        if ( saveDto == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        department.name( saveDto.getName() );
        department.description( saveDto.getDescription() );
        department.address( saveDto.getAddress() );

        return department.build();
    }

    @Override
    public List<SlimDepartmentDto> toListSlimDto(List<Department> departments) {
        if ( departments == null ) {
            return null;
        }

        List<SlimDepartmentDto> list = new ArrayList<SlimDepartmentDto>( departments.size() );
        for ( Department department : departments ) {
            list.add( entityToSlimDto( department ) );
        }

        return list;
    }

    @Override
    public FullDepartmentDto toFullDepartmentDto(Department department) {
        if ( department == null ) {
            return null;
        }

        FullDepartmentDto.FullDepartmentDtoBuilder fullDepartmentDto = FullDepartmentDto.builder();

        fullDepartmentDto.id( department.getId() );
        fullDepartmentDto.name( department.getName() );
        fullDepartmentDto.description( department.getDescription() );
        fullDepartmentDto.address( department.getAddress() );
        fullDepartmentDto.laboratories( laboratoryStructMapper.toListSlimLaboratoryDto( department.getLaboratories() ) );

        return fullDepartmentDto.build();
    }
}
