package com.roger.scheduleservice.service;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.researchcenter.dto.OrderGetDto;

import java.time.LocalDateTime;
import java.util.List;

public interface WebRequestService {
    EquipmentDto getEquipmentByIdFromResearchCenterService(Long equipmentId);
    List<OrderGetDto> getOrderListByEquipmentIdInPeriod(Long equipmentId,
                                                        LocalDateTime startPeriod,
                                                        LocalDateTime endPeriod);
}
