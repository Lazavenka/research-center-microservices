package com.roger.orderservice.controller;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.RentPeriodDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping(value = "/orders")
    public OrderGetDto createOrder(@RequestBody SaveOrderDto saveOrderDto) {
        return orderService.createOrder(saveOrderDto);
    }

    @GetMapping(value = "/orders/{id}")
    public OrderGetDto getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping(value = "/equipment/{equipmentId}/orders")
    public List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(@PathVariable Long equipmentId,
                                                            @RequestParam RentPeriodDto periodDto) {
        return orderService.getOrdersByEquipmentIdAtPeriod(equipmentId, periodDto);
    }

    @GetMapping(value = "/assistants/{assistantId}/orders")
    public List<OrderGetDto> findOrdersByAssistantIdAtPeriod(@PathVariable Long assistantId,
                                                             @RequestParam RentPeriodDto periodDto) {
        return orderService.getOrdersByAssistantIdAtPeriod(assistantId, periodDto);
    }

    @GetMapping(value = "/orders")
    public Map<Long,List<OrderGetDto>> findOrdersByAssistantIdsAtDate(@RequestParam List<Long> assistantIds,
                                                                           @RequestParam LocalDate date) {
        return orderService.getOrdersByAssistantIdsAtDate(assistantIds, date);
    }
}