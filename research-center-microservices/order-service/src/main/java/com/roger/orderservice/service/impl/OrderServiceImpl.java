package com.roger.orderservice.service.impl;

import com.roger.orderservice.dto.EquipmentDto;
import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.mapper.OrderStructMapper;
import com.roger.orderservice.model.Order;
import com.roger.orderservice.model.OrderState;
import com.roger.orderservice.repository.OrderRepository;
import com.roger.orderservice.service.OrderService;
import com.roger.orderservice.service.WebRequestService;
import com.roger.orderservice.util.OrderTotalCostCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private WebRequestService webRequestService;

    @Override
    public OrderGetDto createOrder(SaveOrderDto saveOrderDto) {
        OrderStructMapper mapper = OrderStructMapper.INSTANCE;
        Order order = mapper.saveDtoToEntity(saveOrderDto);
        if (order.getRentStartTime().isAfter(order.getRentEndTime())){
            throw new RuntimeException("Incorrect rent time!");
        }
        System.out.println(order);
        boolean isAvailable = Boolean.TRUE.equals(webRequestService.requestCheckAvailability(order).block());
        if (isAvailable){
            EquipmentDto equipmentDto = webRequestService.requestEquipmentInfo(order.getEquipmentId()).block();
            System.out.println(equipmentDto);
            BigDecimal pricePerHour = equipmentDto.getPricePerHour();
            BigDecimal totalCost = OrderTotalCostCalculator.calculateTotalCost(pricePerHour, order);
            order.setState(OrderState.BOOKED);
            order.setTotalCost(totalCost);
            System.out.println(order);
            Order savedOrder = orderRepository.save(order);
            return mapper.entityToGetDto(savedOrder);
        }else {
            throw new RuntimeException("Not available");
        }
    }

    @Override
    public OrderGetDto getById(Long id) {
        return OrderStructMapper.INSTANCE.entityToGetDto(orderRepository.getReferenceById(id));
    }

    @Override
    public List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(Long equipmentId, LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)){
            return Collections.emptyList();
        }
        List<Order> ordersByEquipmentIdAtPeriod = orderRepository.findOrdersByEquipmentIdAndPeriod(equipmentId,
                startTime, endTime);
        return ordersByEquipmentIdAtPeriod.stream().map(OrderStructMapper.INSTANCE::entityToGetDto).toList();
    }

    @Override
    public List<OrderGetDto> getAll() {
        return OrderStructMapper.INSTANCE.toListDto(orderRepository.findAll());
    }


}
