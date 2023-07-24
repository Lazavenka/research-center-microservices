package com.roger.orderservice.service;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.orderservice.model.Order;

public interface WebRequestService {
    boolean requestCheckAvailability(Order order);
    EquipmentDto requestEquipmentInfo(Long equipmentId);
}
