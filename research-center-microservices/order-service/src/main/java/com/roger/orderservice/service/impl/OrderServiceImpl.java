package com.roger.orderservice.service.impl;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.mapper.OrderStructMapper;
import com.roger.orderservice.model.Order;
import com.roger.orderservice.repository.OrderRepository;
import com.roger.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private WebClient webClient;


    @Override
    public OrderGetDto createOrder(SaveOrderDto saveOrderDto) {
        OrderStructMapper mapper = OrderStructMapper.INSTANCE;
        Order order = mapper.saveDtoToEntity(saveOrderDto);
        boolean isAvailable = Boolean.TRUE.equals(checkAvailability(order).block());
        if (isAvailable){
            Order savedOrder = orderRepository.save(order);
            return mapper.entityToGetDto(savedOrder);
        }
        return null;
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

    public Mono<Boolean> checkAvailability(Order order) {
        String uri = "http://localhost:8082/api/v1/equipment/"
                + order.getEquipmentId() +"/schedule";
        return webClient.post()
                .uri(uri)
                .body(BodyInserters.fromValue(order))
                .retrieve()
                .bodyToMono(Boolean.class);
    }

}
