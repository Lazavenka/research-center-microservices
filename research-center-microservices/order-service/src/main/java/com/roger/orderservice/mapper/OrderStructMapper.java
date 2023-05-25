package com.roger.orderservice.mapper;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderStructMapper {
    OrderStructMapper INSTANCE = Mappers.getMapper(OrderStructMapper.class);

    Order saveDtoToEntity(SaveOrderDto saveOrderDto);
    OrderGetDto entityToGetDto(Order order);
}
