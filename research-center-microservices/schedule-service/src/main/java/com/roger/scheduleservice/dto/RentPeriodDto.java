package com.roger.scheduleservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RentPeriodDto {
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
}
