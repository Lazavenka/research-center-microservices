package com.roger.scheduleservice.model;

import com.roger.researchcenter.dto.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private long id;
    private long clientId;
    private OrderState state;
    private BigDecimal totalCost;
    private long equipmentId;
    private LocalDateTime rentStartTime;
    private LocalDateTime rentEndTime;

    public EquipmentWorkTimePeriod extractWorkTimePeriod(){
        return new EquipmentWorkTimePeriod(rentStartTime, rentEndTime, EquipmentAvailability.BUSY);
    }
}
