package com.roger.orderservice.service.impl;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.RentPeriodDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.mapper.OrderStructMapper;
import com.roger.orderservice.model.Order;
import com.roger.orderservice.repository.OrderRepository;
import com.roger.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private WebClient webClient;


    @Override
    public OrderGetDto createOrder(SaveOrderDto saveOrderDto) {

        RentPeriodDto rentPeriodDto = new RentPeriodDto(saveOrderDto.getRentStartTime(), saveOrderDto.getRentEndTime());
        String uri = buildUri(saveOrderDto.getEquipmentId(), rentPeriodDto);


        boolean isAvailable = Boolean.TRUE.equals(webClient.get()
                .uri(uri).retrieve().bodyToMono(Boolean.class).block());

        if (isAvailable){
            OrderStructMapper mapper = OrderStructMapper.INSTANCE;
            Order order = mapper.saveDtoToEntity(saveOrderDto);
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
        return webClient.post()
                .uri("URL сервиса проверки доступности")
                .body(BodyInserters.fromValue(order))
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    private String buildUri(Long equipmentId, RentPeriodDto rentPeriodDto){
        return  "http://localhost:8082/api/v1/equipment/" + equipmentId.toString() + "/schedule?startPeriod=" +
                rentPeriodDto.getStartPeriod().toString() +
                "&" +
                "endPeriod=" +
                rentPeriodDto.getEndPeriod().toString();
    }
}
