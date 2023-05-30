package by.roger.scheduleservice.data;

import by.roger.scheduleservice.model.Order;
import by.roger.scheduleservice.model.OrderState;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Orders {
    public static final Order ORDER_TO_BE_SAVED = new Order(1, 2, OrderState.BOOKED, new BigDecimal("20.20"),
            5, LocalDateTime.of(2023, 5, 31, 16, 20, 0),
            LocalDateTime.of(2023, 5, 31, 16, 40, 0), 1);

    public static final Order ORDER_NOT_CROSSED_ONE = new Order(2, 3, OrderState.BOOKED, new BigDecimal("20.20"),
            5, LocalDateTime.of(2023, 5, 31, 15, 20, 0),
            LocalDateTime.of(2023, 5, 31, 15, 40, 0), 1);

    public static final Order ORDER_NOT_CROSSED_TWO = new Order(3, 3, OrderState.BOOKED, new BigDecimal("20.20"),
            5, LocalDateTime.of(2023, 5, 31, 17, 20, 0),
            LocalDateTime.of(2023, 5, 31, 17, 40, 0), 1);

    public static final Order ORDER_EXACT_TIME = new Order(4, 3, OrderState.BOOKED, new BigDecimal("20.20"),
            5, LocalDateTime.of(2023, 5, 31, 16, 20, 0),
            LocalDateTime.of(2023, 5, 31, 16, 40, 0), 1);

    public static final Order ORDER_CROSSED_TIME_ONE = new Order(5, 3, OrderState.BOOKED, new BigDecimal("20.20"),
            5, LocalDateTime.of(2023, 5, 31, 16, 0, 0),
            LocalDateTime.of(2023, 5, 31, 16, 50, 0), 1);

    public static final Order ORDER_CROSSED_TIME_TWO = new Order(6, 3, OrderState.BOOKED, new BigDecimal("20.20"),
            5, LocalDateTime.of(2023, 5, 31, 16, 30, 0),
            LocalDateTime.of(2023, 5, 31, 17, 20, 0), 1);

}
