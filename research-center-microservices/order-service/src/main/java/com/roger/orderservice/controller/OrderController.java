package com.roger.orderservice.controller;

import com.roger.researchcenter.dto.OrderGetDto;
import com.roger.orderservice.dto.SaveOrderDto;
import com.roger.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping(value = "/{id}")
    public OrderGetDto getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping(value = "/equipment/{equipmentId}")
    public List<OrderGetDto> getOrdersByEquipmentIdAtPeriod(@PathVariable Long equipmentId,
                                                            @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                                            @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return orderService.getOrdersByEquipmentIdAtPeriod(equipmentId, startTime, endTime);
    }

    @GetMapping
    public ResponseEntity<List<OrderGetDto>> getAll() {
        List<OrderGetDto> orders = orderService.getAll();
        if(orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orders);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PutMapping(value = "/users/{userId}/orders/{orderId}")
    public OrderGetDto payOrder(@PathVariable Long userId,
                                @PathVariable Long orderId) {
        return orderService.payOrder(userId, orderId);
    }
}