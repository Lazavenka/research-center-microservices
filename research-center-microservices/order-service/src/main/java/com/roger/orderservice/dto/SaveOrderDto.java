package com.roger.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
