package com.roger.researchcenterservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "equipment", schema = "research_center")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    private String name;
    private String description;
    @Column(name = "image_file_path")
    private String imageFilePath;
    @Column(name = "equipment_type_id")
    private long equipmentTypeId;
    @Column(name = "laboratory_id")
    private long laboratoryId;
    @Column(name = "is_need_assistant")
    private boolean isNeedAssistant;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private EquipmentState state;
    @Column(name="price_per_hour", precision=5, scale=2)
    private BigDecimal pricePerHour;
    @Column(name="average_research_time")
    private LocalTime averageResearchTime;
}
