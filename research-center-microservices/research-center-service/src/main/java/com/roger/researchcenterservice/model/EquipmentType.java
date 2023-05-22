package com.roger.researchcenterservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "equipment_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentType extends CustomEntity{
    private String name;
    private String description;
    @OneToMany(mappedBy = "equipmentType")
    private List<Equipment> equipment;
}
