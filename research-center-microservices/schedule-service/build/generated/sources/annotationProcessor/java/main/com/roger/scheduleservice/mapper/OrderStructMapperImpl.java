package com.roger.scheduleservice.mapper;

import com.roger.researchcenter.dto.OrderGetDto;
import com.roger.scheduleservice.model.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-21T14:38:26+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class OrderStructMapperImpl implements OrderStructMapper {

    @Override
    public OrderGetDto orderEntityToDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderGetDto.OrderGetDtoBuilder orderGetDto = OrderGetDto.builder();

        orderGetDto.id( order.getId() );
        orderGetDto.clientId( order.getClientId() );
        orderGetDto.state( order.getState() );
        orderGetDto.totalCost( order.getTotalCost() );
        orderGetDto.equipmentId( order.getEquipmentId() );
        orderGetDto.rentStartTime( order.getRentStartTime() );
        orderGetDto.rentEndTime( order.getRentEndTime() );

        return orderGetDto.build();
    }

    @Override
    public Order orderDtoToEntity(OrderGetDto orderGetDto) {
        if ( orderGetDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderGetDto.getId() );
        order.clientId( orderGetDto.getClientId() );
        order.state( orderGetDto.getState() );
        order.totalCost( orderGetDto.getTotalCost() );
        order.equipmentId( orderGetDto.getEquipmentId() );
        order.rentStartTime( orderGetDto.getRentStartTime() );
        order.rentEndTime( orderGetDto.getRentEndTime() );

        return order.build();
    }

    @Override
    public List<Order> listOrderDtoToEntity(List<OrderGetDto> orderGetDtoList) {
        if ( orderGetDtoList == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderGetDtoList.size() );
        for ( OrderGetDto orderGetDto : orderGetDtoList ) {
            list.add( orderDtoToEntity( orderGetDto ) );
        }

        return list;
    }

    @Override
    public List<OrderGetDto> listOrderEntityToDto(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderGetDto> list = new ArrayList<OrderGetDto>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( orderEntityToDto( order ) );
        }

        return list;
    }
}
