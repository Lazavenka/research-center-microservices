package com.roger.scheduleservice.service;

import com.roger.scheduleservice.dto.EquipmentDto;
import com.roger.scheduleservice.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface WebRequestService {
    EquipmentDto getEquipmentByIdFromResearchCenterService(Long equipmentId);
    List<Order> getOrderListByEquipmentIdInPeriod(Long equipmentId,
                                                        LocalDateTime startPeriod,
                                                        LocalDateTime endPeriod);
}
