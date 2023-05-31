package com.roger.orderservice.dto;

import com.roger.orderservice.model.OrderState;

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
public class SaveOrderDto {
    private long clientId;
    private long equipmentId;
    private LocalDateTime rentStartTime;
    private LocalDateTime rentEndTime;
}
