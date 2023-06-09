package by.roger.scheduleservice.service.impl;

import by.roger.scheduleservice.dto.EquipmentDto;
import by.roger.scheduleservice.model.*;

import by.roger.scheduleservice.service.TimeTableService;
import by.roger.scheduleservice.service.request.WebRequestService;
import by.roger.scheduleservice.validator.InputFieldValidator;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@AllArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private WebRequestService requestService;
    private InputFieldValidator inputFieldValidator;

    @Override
    public EquipmentTimeTable provideEquipmentTimeTable(Long equipmentId, LocalDateTime selectedDay) {
        EquipmentTimeTable equipmentTimeTable = new EquipmentTimeTable();
        if (!inputFieldValidator.isCorrectId(equipmentId)) {
            throw new RuntimeException("Equipment ID is not valid!");
        }
        EquipmentDto selectedEquipment = requestService.getEquipmentByIdFromResearchCenterService(equipmentId).block();
        System.out.println(selectedEquipment);
        if (selectedEquipment == null){
            throw new RuntimeException("Equipment not found!");
        }
        equipmentTimeTable.setEquipment(selectedEquipment);

        if (selectedDay.getDayOfWeek() == DayOfWeek.SATURDAY ||
                selectedDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            //LOGGER.log(Level.DEBUG, "Selected date={} is before now() or the day is SATURDAY or SUNDAY", selectedDay);
            return equipmentTimeTable;
        }
        buildTimeTable(equipmentTimeTable, selectedDay);
        List<Order> ordersByEquipmentIdOnSelectedDay = requestService.getOrderListByEquipmentIdInPeriod(equipmentId, selectedDay, selectedDay.plusDays(1)).block();

        if (ordersByEquipmentIdOnSelectedDay == null ||
        ordersByEquipmentIdOnSelectedDay.isEmpty()){
            return equipmentTimeTable;
        } else {
            setAvailability(equipmentTimeTable.getWorkTimePeriods(), ordersByEquipmentIdOnSelectedDay);
        }

        return equipmentTimeTable;
    }

    @Override
    public Mono<Boolean> isAvailableForOrder(Long equipmentId, Order order) {
        LocalDateTime orderStartTime = order.getRentStartTime();
        LocalDateTime orderEndTime = order.getRentEndTime();
        Mono<List<Order>> ordersInPeriod = requestService.getOrderListByEquipmentIdInPeriod(equipmentId, orderStartTime, orderEndTime);
        return ordersInPeriod.map(list -> checkIsAvailable(order, list));
    }


    public boolean checkIsAvailable(Order orderToBeChecked, List<Order> ordersInPeriod) {
        return ordersInPeriod.stream()
                .map(Order::extractWorkTimePeriod)
                .filter(period -> period.crossPeriod(orderToBeChecked.extractWorkTimePeriod()))
                .toList().isEmpty();
    }


    private void buildTimeTable(EquipmentTimeTable equipmentTimeTable, LocalDateTime currentDate) {
        LocalTime averageResearchTime = equipmentTimeTable.getEquipment().getAverageResearchTime();
        int averageResearchTimeHours = averageResearchTime.getHour();
        int averageResearchTimeMinutes = averageResearchTime.getMinute();
        LocalTime endWorkingDay = EquipmentTimeTable.END_WORKING_TIME;
        LocalTime startCurrentPeriod = EquipmentTimeTable.START_WORKING_TIME;
        boolean dayNotFinished = true;
        while (dayNotFinished) {
            LocalDateTime startDateTimePeriod = currentDate.withHour(startCurrentPeriod.getHour())
                    .withMinute(startCurrentPeriod.getMinute())
                    .withSecond(startCurrentPeriod.getSecond())
                    .withNano(startCurrentPeriod.getNano());
            LocalTime endCurrentTimePeriod = startCurrentPeriod.plus(averageResearchTimeHours, ChronoUnit.HOURS)
                    .plus(averageResearchTimeMinutes, ChronoUnit.MINUTES);
            if (endCurrentTimePeriod.isAfter(endWorkingDay)) {
                dayNotFinished = false;
            } else {
                EquipmentAvailability availability;
                if (startDateTimePeriod.isBefore(LocalDateTime.now())) {
                    availability = EquipmentAvailability.PAST;
                } else {

                    availability = EquipmentAvailability.AVAILABLE;
                }
                LocalDateTime endDateTimePeriod = currentDate.withHour(endCurrentTimePeriod.getHour())
                        .withMinute(endCurrentTimePeriod.getMinute())
                        .withSecond(endCurrentTimePeriod.getSecond())
                        .withNano(endCurrentTimePeriod.getNano());
                EquipmentWorkTimePeriod currentPeriod = new EquipmentWorkTimePeriod(startDateTimePeriod, endDateTimePeriod, availability);
                equipmentTimeTable.getWorkTimePeriods().add(currentPeriod);
            }
            startCurrentPeriod = endCurrentTimePeriod;
        }
    }
    private void setAvailability(List<EquipmentWorkTimePeriod> timeTable, List<Order> equipmentOrders){
        for (Order equipmentOrder : equipmentOrders) {
            if (equipmentOrder.getState() != OrderState.CANCELLED) {
                EquipmentWorkTimePeriod equipmentOrderTimePeriod = equipmentOrder.extractWorkTimePeriod();
                for (EquipmentWorkTimePeriod currentPeriod : timeTable) {
                    if (currentPeriod.crossPeriod(equipmentOrderTimePeriod)) {
                        currentPeriod.setAvailability(EquipmentAvailability.BUSY);
                    }
                }
            }
        }
    }

}
