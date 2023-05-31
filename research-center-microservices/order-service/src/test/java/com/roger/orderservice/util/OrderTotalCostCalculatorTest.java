package com.roger.orderservice.util;

import com.roger.orderservice.model.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTotalCostCalculatorTest {

    private final BigDecimal pricePerHour = new BigDecimal("20.00");
    @Test
    void calculateTotalCost() {
        BigDecimal expectedTotalCost = new BigDecimal("15.00");
        Order order = Order.builder()
                .rentStartTime(LocalDateTime.of(2023,5,31,16,0))
                .rentEndTime(LocalDateTime.of(2023,5,31,16,45))
        .build();

        BigDecimal actual = OrderTotalCostCalculator.calculateTotalCost(pricePerHour, order);
        assertEquals(expectedTotalCost, actual);
    }
    @Test
    void calculateTotalCost_another() {
        BigDecimal expectedTotalCost = new BigDecimal("55.00");
        Order order = Order.builder()
                .rentStartTime(LocalDateTime.of(2023,5,31,16,0))
                .rentEndTime(LocalDateTime.of(2023,5,31,18,45))
                .build();

        BigDecimal actual = OrderTotalCostCalculator.calculateTotalCost(pricePerHour, order);
        assertEquals(expectedTotalCost, actual);
    }
}