package com.roger.scheduleservice.service.impl;

import com.roger.microservices.dto.OrderGetDto;
import com.roger.scheduleservice.mapper.OrderStructMapper;
import com.roger.scheduleservice.model.Order;
import com.roger.scheduleservice.data.OrdersData;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeTableServiceImplTest {

    private OrderStructMapper mapper = OrderStructMapper.INSTANCE;

    private final TimeTableServiceImpl timeTableService = new TimeTableServiceImpl(
                        new WebRequestServiceImpl(WebClient.builder().build()), mapper);

    @Test
    void checkAvailabilityTest_notCrossed() {
        List<Order> ordersInPeriod = new ArrayList<>();

        ordersInPeriod.add(OrdersData.ORDER_NOT_CROSSED_ONE);
        ordersInPeriod.add(OrdersData.ORDER_NOT_CROSSED_TWO);

        assertTrue(timeTableService.checkIsAvailable(OrdersData.ORDER_TO_BE_SAVED, ordersInPeriod));

    }

    @Test
    void checkAvailabilityTest_exactTime() {

        List<Order> ordersInPeriod = new ArrayList<>();

        ordersInPeriod.add(OrdersData.ORDER_NOT_CROSSED_ONE);
        ordersInPeriod.add(OrdersData.ORDER_EXACT_TIME);

        assertFalse(timeTableService.checkIsAvailable(OrdersData.ORDER_TO_BE_SAVED, ordersInPeriod));

    }

    @Test
    void checkAvailabilityTest_crossedOne() {

        List<Order> ordersInPeriod = new ArrayList<>();

        ordersInPeriod.add(OrdersData.ORDER_NOT_CROSSED_ONE);
        ordersInPeriod.add(OrdersData.ORDER_CROSSED_TIME_ONE);

        assertFalse(timeTableService.checkIsAvailable(OrdersData.ORDER_TO_BE_SAVED, ordersInPeriod));

    }

    @Test
    void checkAvailabilityTest_crossedTwo() {

        List<Order> ordersInPeriod = new ArrayList<>();

        ordersInPeriod.add(OrdersData.ORDER_NOT_CROSSED_ONE);
        ordersInPeriod.add(OrdersData.ORDER_CROSSED_TIME_TWO);

        assertFalse(timeTableService.checkIsAvailable(OrdersData.ORDER_TO_BE_SAVED, ordersInPeriod));

    }
}