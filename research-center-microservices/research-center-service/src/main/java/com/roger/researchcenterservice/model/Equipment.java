package com.roger.researchcenterservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "equipment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment extends CustomEntity{

    private String name;
    private String description;
    @Column(name = "image_file_path")
    private String imageFilePath;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "laboratory_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Laboratory laboratory;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipment_type_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private EquipmentType equipmentType;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private EquipmentState state;
    @Column(name="price_per_hour", precision=5, scale=2)
    private BigDecimal pricePerHour;
    @Column(name="average_research_time")
    @Temporal(TemporalType.TIME)
    private LocalTime averageResearchTime;
}
