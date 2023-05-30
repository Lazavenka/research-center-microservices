package com.roger.orderservice.repository;


import com.roger.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.equipmentId = :equipmentId " +
            "AND (o.rentStartTime >= :startPeriod AND o.rentStartTime <= :endPeriod)")
    List<Order> findOrdersByEquipmentIdAndPeriod(Long equipmentId, LocalDateTime startPeriod, LocalDateTime endPeriod);

}
