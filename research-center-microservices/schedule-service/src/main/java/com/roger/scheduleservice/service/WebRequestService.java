package com.roger.scheduleservice.service;

import com.roger.microservices.dto.EquipmentDto;
import com.roger.microservices.dto.OrderGetDto;

import java.time.LocalDateTime;
import java.util.List;

public interface WebRequestService {
    EquipmentDto getEquipmentByIdFromResearchCenterService(Long equipmentId);
    List<OrderGetDto> getOrderListByEquipmentIdInPeriod(Long equipmentId,
                                                        LocalDateTime startPeriod,
                                                        LocalDateTime endPeriod);
}
