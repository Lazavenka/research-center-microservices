package com.roger.orderservice.util;

import com.roger.orderservice.model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;

public class OrderTotalCostCalculator {
    private static final int MINUTES_IN_HOUR = 60;

    private OrderTotalCostCalculator(){
    }

    public static BigDecimal calculateTotalCost(BigDecimal pricePerHour, Order order) {
        LocalTime startTime = order.getRentStartTime().toLocalTime();
        LocalTime endTime = order.getRentEndTime().toLocalTime();

        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;


        BigDecimal bigDecimalHoursPart = BigDecimal.valueOf(hours);
        BigDecimal bigDecimalMinutesPart = BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(MINUTES_IN_HOUR), 4, RoundingMode.CEILING);
        BigDecimal timeInHours = bigDecimalHoursPart.add(bigDecimalMinutesPart);
        return pricePerHour.multiply(timeInHours).setScale(2, RoundingMode.CEILING);
    }

}
