package com.roger.scheduleservice.service.impl;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.researchcenter.dto.OrderGetDto;
import com.roger.researchcenter.dto.OrderState;
import com.roger.researchcenter.exception.IncorrectRequestException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;

import com.roger.scheduleservice.mapper.OrderStructMapper;
import com.roger.scheduleservice.model.*;
import com.roger.scheduleservice.service.TimeTableService;
import com.roger.scheduleservice.validator.InputFieldValidator;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@AllArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private WebRequestServiceImpl requestService;
    private OrderStructMapper mapper;

    @Override
    public EquipmentTimeTable provideEquipmentTimeTable(Long equipmentId, LocalDate selectedDay) {
        if (!InputFieldValidator.isCorrectId(equipmentId)) {
            throw new IncorrectRequestException(ServiceLayerExceptionCodes.INCORRECT_EQUIPMENT_ID);
        }
        EquipmentTimeTable equipmentTimeTable = new EquipmentTimeTable();
        EquipmentDto selectedEquipment = requestService.getEquipmentByIdFromResearchCenterService(equipmentId);

        equipmentTimeTable.setEquipment(selectedEquipment);

        if (selectedDay.getDayOfWeek() == DayOfWeek.SATURDAY ||
                selectedDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return equipmentTimeTable;
        }
        buildTimeTable(equipmentTimeTable, selectedDay);
        List<OrderGetDto> ordersDtoByEquipmentIdOnSelectedDay = requestService
                .getOrderListByEquipmentIdInPeriod(equipmentId, selectedDay.atStartOfDay(), selectedDay.plusDays(1).atStartOfDay());

        if (ordersDtoByEquipmentIdOnSelectedDay == null || ordersDtoByEquipmentIdOnSelectedDay.isEmpty()){
            return equipmentTimeTable;
        } else {
            List<Order> ordersListOnSelectedDay = mapper.listOrderDtoToEntity(ordersDtoByEquipmentIdOnSelectedDay);
            setAvailability(equipmentTimeTable.getWorkTimePeriods(), ordersListOnSelectedDay);
        }

        return equipmentTimeTable;
    }

    @Override
    public Mono<Boolean> isAvailableForOrder(Long equipmentId, Order order) {
        LocalDateTime orderEndTime = order.getRentEndTime();
        if (orderEndTime.isBefore(LocalDateTime.now()))
        {
            return Mono.just(Boolean.FALSE);
        }
        LocalDateTime orderStartTime = order.getRentStartTime();
        List<OrderGetDto> ordersGetDtoInPeriod = requestService.getOrderListByEquipmentIdInPeriod(equipmentId, orderStartTime, orderEndTime);
        List<Order> ordersInPeriod = mapper.listOrderDtoToEntity(ordersGetDtoInPeriod);
        return Mono.just(checkIsAvailable(order, ordersInPeriod));
    }


    public boolean checkIsAvailable(Order orderToBeChecked, List<Order> ordersInPeriod) {
        return ordersInPeriod.stream()
                .map(Order::extractWorkTimePeriod)
                .filter(period -> period.crossPeriod(orderToBeChecked.extractWorkTimePeriod()))
                .toList().isEmpty();
    }


    private void buildTimeTable(EquipmentTimeTable equipmentTimeTable, LocalDate currentDate) {
        LocalTime averageResearchTime = equipmentTimeTable.getEquipment().getAverageResearchTime();
        int averageResearchTimeHours = averageResearchTime.getHour();
        int averageResearchTimeMinutes = averageResearchTime.getMinute();

        LocalDateTime startDateTimePeriod = currentDate.atTime(EquipmentTimeTable.START_WORKING_TIME);
        LocalDateTime endWorkingDay = currentDate.atTime(EquipmentTimeTable.END_WORKING_TIME);

        boolean dayFinished = false;
        while (!dayFinished) {
            LocalDateTime endCurrentDateTimePeriod = startDateTimePeriod.plus(averageResearchTimeHours, ChronoUnit.HOURS)
                    .plus(averageResearchTimeMinutes, ChronoUnit.MINUTES);
            if (endCurrentDateTimePeriod.isBefore(endWorkingDay) || endCurrentDateTimePeriod.equals(endWorkingDay)) {
                EquipmentAvailability availability;
                if (startDateTimePeriod.isBefore(LocalDateTime.now())) {
                    availability = EquipmentAvailability.PAST;
                } else {
                    availability = EquipmentAvailability.AVAILABLE;
                }
                EquipmentWorkTimePeriod currentPeriod = new EquipmentWorkTimePeriod(startDateTimePeriod, endCurrentDateTimePeriod, availability);
                equipmentTimeTable.getWorkTimePeriods().add(currentPeriod);
            } else {
                dayFinished = true;
            }
            startDateTimePeriod = endCurrentDateTimePeriod;
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
