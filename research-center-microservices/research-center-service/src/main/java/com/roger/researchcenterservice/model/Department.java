package com.roger.researchcenterservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department extends CustomEntity{
    private String name;
    private String description;
    private String address;
    @OneToMany(mappedBy = "department")
    private List<Laboratory> laboratories;
}
