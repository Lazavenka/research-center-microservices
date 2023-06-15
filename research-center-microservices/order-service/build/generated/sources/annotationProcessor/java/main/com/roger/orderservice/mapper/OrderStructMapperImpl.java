package com.roger.orderservice.mapper;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.model.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-14T14:18:43+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class OrderStructMapperImpl implements OrderStructMapper {

    @Override
    public Order saveDtoToEntity(SaveOrderDto saveOrderDto) {
        if ( saveOrderDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.clientId( saveOrderDto.getClientId() );
        order.equipmentId( saveOrderDto.getEquipmentId() );
        order.rentStartTime( saveOrderDto.getRentStartTime() );
        order.rentEndTime( saveOrderDto.getRentEndTime() );

        return order.build();
    }

    @Override
    public OrderGetDto entityToGetDto(Order order) {
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
    public List<OrderGetDto> toListDto(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderGetDto> list = new ArrayList<OrderGetDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( entityToGetDto( order ) );
        }

        return list;
    }
}
