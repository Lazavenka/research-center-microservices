package by.roger.scheduleservice.model;

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
    private long assistantId;
}
