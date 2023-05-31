package com.roger.orderservice.mapper;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.model.Order;
import org.aspectj.weaver.ast.Or;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderStructMapper {
    OrderStructMapper INSTANCE = Mappers.getMapper(OrderStructMapper.class);

    Order saveDtoToEntity(SaveOrderDto saveOrderDto);
    OrderGetDto entityToGetDto(Order order);
    List<OrderGetDto> toListDto(List<Order> orders);
}
