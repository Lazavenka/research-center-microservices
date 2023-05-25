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
public class RentPeriodDto {
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
}
