package com.roger.scheduleservice.mapper;

import com.roger.researchcenter.dto.OrderGetDto;
import com.roger.scheduleservice.model.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderStructMapper {
    OrderStructMapper INSTANCE = Mappers.getMapper(OrderStructMapper.class);

    OrderGetDto orderEntityToDto(Order order);
    Order orderDtoToEntity(OrderGetDto orderGetDto);
    List<Order> listOrderDtoToEntity(List<OrderGetDto> orderGetDtoList);
    List<OrderGetDto> listOrderEntityToDto(List<Order> orderList);

}
