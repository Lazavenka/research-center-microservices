package by.roger.scheduleservice.service.impl;

import by.roger.scheduleservice.model.Order;
import by.roger.scheduleservice.service.request.WebRequestService;
import by.roger.scheduleservice.validator.InputFieldValidator;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static by.roger.scheduleservice.data.Orders.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeTableServiceImplTest {

    private final TimeTableServiceImpl timeTableService = new TimeTableServiceImpl(
                        new WebRequestService(WebClient.builder().build()), new InputFieldValidator());

    @Test
    void checkAvailabilityTest_notCrossed() {
        List<Order> ordersInPeriod = new ArrayList<>();

        ordersInPeriod.add(ORDER_NOT_CROSSED_ONE);
        ordersInPeriod.add(ORDER_NOT_CROSSED_TWO);

        assertTrue(timeTableService.checkIsAvailable(ORDER_TO_BE_SAVED, ordersInPeriod));



    }

    @Test
    void checkAvailabilityTest_exactTime() {

        List<Order> ordersInPeriod = new ArrayList<>();

        ordersInPeriod.add(ORDER_NOT_CROSSED_ONE);
        ordersInPeriod.add(ORDER_EXACT_TIME);

        assertFalse(timeTableService.checkIsAvailable(ORDER_TO_BE_SAVED, ordersInPeriod));

    }

    @Test
    void checkAvailabilityTest_crossedOne() {

        List<Order> ordersInPeriod = new ArrayList<>();

        ordersInPeriod.add(ORDER_NOT_CROSSED_ONE);
        ordersInPeriod.add(ORDER_CROSSED_TIME_ONE);

        assertFalse(timeTableService.checkIsAvailable(ORDER_TO_BE_SAVED, ordersInPeriod));

    }

    @Test
    void checkAvailabilityTest_crossedTwo() {

        List<Order> ordersInPeriod = new ArrayList<>();

        ordersInPeriod.add(ORDER_NOT_CROSSED_ONE);
        ordersInPeriod.add(ORDER_CROSSED_TIME_TWO);

        assertFalse(timeTableService.checkIsAvailable(ORDER_TO_BE_SAVED, ordersInPeriod));

    }
}