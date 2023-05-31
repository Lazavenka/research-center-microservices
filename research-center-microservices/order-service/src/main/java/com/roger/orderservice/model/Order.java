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
@ToString
public class Order extends CustomEntity {
    @Column(name="client_id")
    private long clientId;
    @Column(name="order_state")
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @Column(name="total_cost", precision=5, scale=2)
    private BigDecimal totalCost;
    @Column(name="equipment_id")
    private long equipmentId;
    @Column(name="rent_start_time")
    private LocalDateTime rentStartTime;
    @Column(name="rent_end_time")
    private LocalDateTime rentEndTime;
}
