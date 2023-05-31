package com.roger.scheduleservice.service;

import com.roger.scheduleservice.dto.EquipmentDto;
import com.roger.scheduleservice.model.Order;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

public interface WebRequestService {
    Mono<EquipmentDto> getEquipmentByIdFromResearchCenterService(Long equipmentId);
    Mono<List<Order>> getOrderListByEquipmentIdInPeriod(Long equipmentId,
                                                        LocalDateTime startPeriod,
                                                        LocalDateTime endPeriod);
}
