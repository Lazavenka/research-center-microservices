package com.roger.orderservice.controller;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.RentPeriodDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public OrderGetDto createOrder(@RequestBody SaveOrderDto saveOrderDto) {
        return orderService.createOrder(saveOrderDto);
    }

    @GetMapping(value = "{/id}")
    public OrderGetDto getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping(value = "/equipment/{equipmentId}")
    public List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(@PathVariable Long equipmentId,
                                                            @RequestParam RentPeriodDto periodDto) {
        return orderService.getOrdersByEquipmentIdAtPeriod(equipmentId, periodDto);
    }

    @GetMapping(value = "/assistant/{assistantId}")
    public List<OrderGetDto> findOrdersByAssistantIdAtPeriod(@PathVariable Long assistantId,
                                                             @RequestParam RentPeriodDto periodDto) {
        return orderService.getOrdersByAssistantIdAtPeriod(assistantId, periodDto);
    }
}