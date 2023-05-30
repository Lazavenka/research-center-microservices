package by.roger.scheduleservice.service.impl;

import by.roger.scheduleservice.model.*;

import by.roger.scheduleservice.service.TimeTableService;
import by.roger.scheduleservice.service.request.WebRequestService;
import by.roger.scheduleservice.validator.InputFieldValidator;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
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
    public Optional<EquipmentTimeTable> provideEquipmentTimeTable(Long equipmentId, LocalDateTime selectedDay) {
        Optional<EquipmentTimeTable> optionalEquipmentTimeTable = Optional.empty();
        if (!inputFieldValidator.isCorrectId(equipmentId) || !inputFieldValidator.isDateTimeNotPast(selectedDay)) {
            return optionalEquipmentTimeTable;
        }

        if (selectedDay.getDayOfWeek() == DayOfWeek.SATURDAY ||
                selectedDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            //LOGGER.log(Level.DEBUG, "Selected date={} is before now() or the day is SATURDAY or SUNDAY", selectedDay);
            return optionalEquipmentTimeTable;
        }

        Mono<Equipment> selectedEquipment = requestService.getEquipmentByIdFromResearchCenterService(equipmentId);

        Long laboratoryId = selectedEquipment.map(Equipment::getLaboratoryId).block();

        Mono<List<Assistant>> assistantFlux = requestService.getAssistantsByLaboratoryIdFromResearchCenterService(laboratoryId);


        EquipmentTimeTable equipmentTimeTable = new EquipmentTimeTable(selectedEquipment);

        WebClient.ResponseSpec ordersResponse = webClient.get()
                .uri("get from order service findOrdersByEquipmentIdAtPeriod")
                .retrieve();

        List<Order> ordersByEquipmentIdOnSelectedDay = ordersResponse.bodyToFlux(Order.class)
                .collectList()
                .block();

        Map<Long, List<Order>> assistantsOrders = new HashMap<>();
        for (Assistant assistant : assistants) {
            long assistantId = assistant.getAssistantId();
            WebClient.ResponseSpec assistantOrdersOnSelectedDayResponse = webClient.get()
                    .uri("get from order service findOrdersByAssistantIdAtPeriod")
                    .retrieve();

            List<Order> assistantOrdersOnSelectedDay = assistantOrdersOnSelectedDayResponse.bodyToFlux(Order.class)
                    .collectList()
                    .block();


            assistantsOrders.put(assistantId, assistantOrdersOnSelectedDay);
        }

        buildTimeTable(assistants, equipmentTimeTable, selectedDay, 1);
        setAvailability(equipmentTimeTable.getWorkTimePeriods(), ordersByEquipmentIdOnSelectedDay, assistantsOrders);
        return Optional.of(equipmentTimeTable);
    }

    @Override
    public Mono<Boolean> isAvailableForOrder(Long equipmentId, Order order) {
        Mono<List<Order>> ordersInPeriod = getOrderListByEquipmentIdInPeriod(equipmentId, order);

        return ordersInPeriod.map(list -> checkIsAvailable(order, list));
    }

    private Mono<List<Order>> getOrderListByEquipmentIdInPeriod(Long equipmentId, Order order) {
        LocalDateTime orderRentStartDate = order.getRentStartTime();
        LocalDateTime orderRentEndTime = order.getRentStartTime();
        String uri = "http://localhost:8081/api/v1/equipment/" + equipmentId.toString() + "/order?startPeriod=" +
                orderRentStartDate.toString() +
                "&" +
                "endPeriod=" +
                orderRentEndTime.toString();
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Order.class)
                .collectList();
    }

    public boolean checkIsAvailable(Order orderToBeChecked, List<Order> ordersInPeriod){
        return ordersInPeriod.stream()
                .map(Order::extractWorkTimePeriod)
                .filter(period -> period.crossPeriod(orderToBeChecked.extractWorkTimePeriod()))
                .toList().isEmpty();
    }

    private void buildTimeTable(List<Assistant> laboratoryAssistants, EquipmentTimeTable equipmentTimeTable) {
        if (equipmentTimeTable.getEquipmentDto() == null) {
            throw new RuntimeException("Error in EquipmentTimeTableService. Equipment parameter can't be null!");
        }
        int dayCount = EquipmentTimeTable.SEVEN_DAYS;
        LocalDateTime currentDate = LocalDateTime.now();
        buildTimeTable(laboratoryAssistants, equipmentTimeTable, currentDate, dayCount);
    }

    private void buildTimeTable(List<Assistant> laboratoryAssistants, EquipmentTimeTable equipmentTimeTable, LocalDateTime startDate, int daysCount) {
        LocalTime averageResearchTime = equipmentTimeTable.getEquipmentDto().getAverageResearchTime();
        int averageResearchTimeHours = averageResearchTime.getHour();
        int averageResearchTimeMinutes = averageResearchTime.getMinute();
        LocalTime endWorkingDay = EquipmentTimeTable.END_WORKING_TIME;
        LocalDateTime currentDate = startDate;
        for (int i = 0; i < daysCount; i++) {
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
                        availability = EquipmentAvailability.FULL_AVAILABLE;
                    }
                    LocalDateTime endDateTimePeriod = currentDate.withHour(endCurrentTimePeriod.getHour())
                            .withMinute(endCurrentTimePeriod.getMinute())
                            .withSecond(endCurrentTimePeriod.getSecond())
                            .withNano(endCurrentTimePeriod.getNano());
                    EquipmentWorkTimePeriod currentPeriod = new EquipmentWorkTimePeriod(startDateTimePeriod, endDateTimePeriod, availability, laboratoryAssistants);
                    equipmentTimeTable.getWorkTimePeriods().add(currentPeriod);
                }
                startCurrentPeriod = endCurrentTimePeriod;
            }
            currentDate = currentDate.plusDays(1);
            if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                currentDate = currentDate.plusDays(2);
            }
        }
    }
//todo придумать более простой алгоритм
    private void setAvailability(List<EquipmentWorkTimePeriod> timeTable, List<Order> equipmentOrders, Map<Long, List<Order>> assistantsOrders) {
        for (Map.Entry<Long, List<Order>> currentAssistantOrders : assistantsOrders.entrySet()) {
            List<Order> currentOrders = currentAssistantOrders.getValue();
            Long currentAssistantId = currentAssistantOrders.getKey();
            for (Order assistantOrder : currentOrders) {
                if (assistantOrder.getState() != OrderState.CANCELLED) {
                    LocalDateTime orderStart = assistantOrder.getRentStartTime();
                    LocalDateTime orderEnd = assistantOrder.getRentEndTime();
                    EquipmentWorkTimePeriod assistantOrderTimePeriod = new EquipmentWorkTimePeriod(orderStart, orderEnd, EquipmentAvailability.AVAILABLE_WITHOUT_ASSISTANT, Collections.emptyList());
                    for (EquipmentWorkTimePeriod currentPeriod : timeTable) {
                        if (currentPeriod.crossPeriod(assistantOrderTimePeriod)) {
                            currentPeriod.removeAssistantById(currentAssistantId);
                        }
                    }
                }
            }
        }
        for (EquipmentWorkTimePeriod currentPeriod : timeTable) {
            if (currentPeriod.getStartOfPeriod().isAfter(LocalDateTime.now()) && currentPeriod.getAvailableAssistantInPeriod().isEmpty()) {
                currentPeriod.setAvailability(EquipmentAvailability.AVAILABLE_WITHOUT_ASSISTANT);
            }
        }
        for (Order equipmentOrder : equipmentOrders) {
            if (equipmentOrder.getState() != OrderState.CANCELLED) {
                LocalDateTime orderStart = equipmentOrder.getRentStartTime();
                LocalDateTime orderEnd = equipmentOrder.getRentEndTime();
                EquipmentWorkTimePeriod equipmentOrderTimePeriod = new EquipmentWorkTimePeriod(orderStart, orderEnd, EquipmentAvailability.BUSY, Collections.emptyList());
                for (EquipmentWorkTimePeriod currentPeriod : timeTable) {
                    if (currentPeriod.crossPeriod(equipmentOrderTimePeriod)) {
                        currentPeriod.setAvailability(EquipmentAvailability.BUSY);
                    }
                }
            }
        }
    }

}
