package com.roger.scheduleservice;

import com.roger.scheduleservice.data.EquipmentData;
import com.roger.scheduleservice.data.OrdersData;
import com.roger.scheduleservice.data.TestDateTimeData;
import com.roger.scheduleservice.mapper.OrderStructMapper;
import com.roger.scheduleservice.model.EquipmentTimeTable;
import com.roger.scheduleservice.service.impl.TimeTableServiceImpl;
import com.roger.scheduleservice.service.impl.WebRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceApplicationTest {

    @Mock
    private WebRequestServiceImpl webRequestService = Mockito.mock(WebRequestServiceImpl.class);

    private final OrderStructMapper orderStructMapper = OrderStructMapper.INSTANCE;

    private TimeTableServiceImpl timeTableService;

    @BeforeEach
    void setup() {
        timeTableService = new TimeTableServiceImpl(webRequestService, orderStructMapper);
    }

    @Test
    void testWorkTimePeriodsCount() {
        when(webRequestService.getEquipmentByIdFromResearchCenterService(1L))
                .thenReturn(EquipmentData.EQUIPMENT_ONE_HOUR_RESEARCH_TIME);
        when(webRequestService.getOrderListByEquipmentIdInPeriod(1L, TestDateTimeData.WEDNESDAY, TestDateTimeData.THURSDAY))
                .thenReturn(orderStructMapper.listOrderEntityToDto(List.of(OrdersData.ORDER_TO_BE_SAVED, OrdersData.ORDER_EXACT_TIME)));

        EquipmentTimeTable actual = timeTableService.provideEquipmentTimeTable(1L, TestDateTimeData.WEDNESDAY.toLocalDate());

        assertEquals(12, actual.getWorkTimePeriods().size());
    }

    @Test
    void testWorkTimePeriodsCount_requestSaturday() {
        when(webRequestService.getEquipmentByIdFromResearchCenterService(1L))
                .thenReturn(EquipmentData.EQUIPMENT_TWO_HOUR_RESEARCH_TIME);
/*
        when(webRequestService.getOrderListByEquipmentIdInPeriod(1L, TestDateTimeData.SATURDAY, TestDateTimeData.SUNDAY))
                .thenReturn(Mono.just(List.of(OrdersData.ORDER_EXACT_TIME, OrdersData.ORDER_TO_BE_SAVED)));

        this stubbing method will produce
        org.mockito.exceptions.misusing.UnnecessaryStubbingException:
            Unnecessary stubbings detected.
        because in case we call provideEquipmentTimeTable(long, SATURDAY/SUNDAY)
            webRequestService.getOrderListByEquipmentIdInPeriod(1L, TestDateTimeData.SATURDAY, TestDateTimeData.SUNDAY))
            does not invoke
 */
        EquipmentTimeTable actual = timeTableService.provideEquipmentTimeTable(1L, TestDateTimeData.SATURDAY.toLocalDate());

        assertTrue(actual.getWorkTimePeriods().isEmpty());
    }

    @Test
    void testWorkTimePeriodsCount_twoHourResearchTime() {
        when(webRequestService.getEquipmentByIdFromResearchCenterService(2L))
                .thenReturn(EquipmentData.EQUIPMENT_TWO_HOUR_RESEARCH_TIME);
        when(webRequestService.getOrderListByEquipmentIdInPeriod(2L, TestDateTimeData.THURSDAY, TestDateTimeData.FRIDAY))
                .thenReturn(Collections.emptyList());

        EquipmentTimeTable actual = timeTableService.provideEquipmentTimeTable(2L, TestDateTimeData.THURSDAY.toLocalDate());

        assertEquals(6, actual.getWorkTimePeriods().size());
    }


    @Test
    void testWorkTimePeriodsCount_fourHourResearchTime() {
        when(webRequestService.getEquipmentByIdFromResearchCenterService(3L))
                .thenReturn(EquipmentData.EQUIPMENT_FOUR_HOUR_RESEARCH_TIME);
        when(webRequestService.getOrderListByEquipmentIdInPeriod(3L, TestDateTimeData.THURSDAY, TestDateTimeData.FRIDAY))
                .thenReturn(Collections.emptyList());

        EquipmentTimeTable actual = timeTableService.provideEquipmentTimeTable(3L, TestDateTimeData.THURSDAY.toLocalDate());

        assertEquals(3, actual.getWorkTimePeriods().size());
    }
}