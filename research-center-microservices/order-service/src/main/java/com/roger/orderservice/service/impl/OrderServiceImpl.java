package com.roger.orderservice.service.impl;

import com.roger.orderservice.dto.EquipmentDto;
import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.exception.CustomNotFoundException;
import com.roger.orderservice.exception.IncorrectRequestException;
import com.roger.orderservice.mapper.OrderStructMapper;
import com.roger.orderservice.model.Order;
import com.roger.orderservice.model.OrderState;
import com.roger.orderservice.repository.OrderRepository;
import com.roger.orderservice.service.OrderService;
import com.roger.orderservice.service.ServiceLayerExceptionCodes;
import com.roger.orderservice.service.WebRequestService;
import com.roger.orderservice.util.OrderTotalCostCalculator;
import com.roger.orderservice.util.impl.OrderValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private WebRequestService webRequestService;
    private OrderStructMapper mapper;
    private OrderValidator validator;

    @Override
    public OrderGetDto createOrder(SaveOrderDto saveOrderDto) {
        validator.validate(saveOrderDto);
        Order order = mapper.saveDtoToEntity(saveOrderDto);
        Long equipmentId = order.getEquipmentId();
        Boolean isAvailable = webRequestService.requestCheckAvailability(order);
        if (Boolean.TRUE.equals(isAvailable)){
            EquipmentDto requestedEquipmentInfo = webRequestService.requestEquipmentInfo(equipmentId);
            BigDecimal pricePerHour = requestedEquipmentInfo.getPricePerHour();
            BigDecimal totalCost = OrderTotalCostCalculator.calculateTotalCost(pricePerHour, order);
            order.setState(OrderState.BOOKED);
            order.setTotalCost(totalCost);
            Order savedOrder = orderRepository.save(order);
            return mapper.entityToGetDto(savedOrder);
        }else {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.NOT_AVAILABLE);
        }
    }

    @Override
    public OrderGetDto getById(Long id) {
        validator.validateId(id);
        try {
            return mapper.entityToGetDto(orderRepository.getReferenceById(id));
        } catch (EntityNotFoundException exception) {
            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_ID, id);
        }
    }

    @Override
    public List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(Long equipmentId,
                                                            LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            return Collections.emptyList();
        }
        List<Order> ordersByEquipmentIdAtPeriod = orderRepository.findOrdersByEquipmentIdAndPeriod(equipmentId,
                startTime, endTime);
        return ordersByEquipmentIdAtPeriod.stream().map(mapper::entityToGetDto).toList();
    }

    @Override
    public List<OrderGetDto> getAll() {
        return mapper.toListDto(orderRepository.findAll());
    }

    @Override
    public OrderGetDto payOrder(Long userId, Long orderId) {
        return null; //todo implement with checking balance and security
    }


}
