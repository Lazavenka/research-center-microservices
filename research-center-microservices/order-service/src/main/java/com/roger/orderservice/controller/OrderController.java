package com.roger.orderservice.controller;

import com.roger.orderservice.dto.OrderGetDto;
import com.roger.orderservice.dto.RentPeriodDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                                                            @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime startTime,
                                                            @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime endTime) {
        return orderService.getOrdersByEquipmentIdAtPeriod(equipmentId, startTime, endTime);
    }
    @GetMapping(value = "/orders")
    public List<OrderGetDto> getAll(){
        return orderService.getAll();
    }
}