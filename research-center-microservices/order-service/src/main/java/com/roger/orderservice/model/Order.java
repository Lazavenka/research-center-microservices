package com.roger.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends CustomEntity {
    private long clientId;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @Column(name="total_cost", precision=5, scale=2)
    private BigDecimal totalCost;
    private long equipmentId;
    private LocalDateTime rentStartTime;
    private LocalDateTime rentEndTime;
    private long assistantId;
}
