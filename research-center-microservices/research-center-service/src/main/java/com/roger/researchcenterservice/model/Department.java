package com.roger.researchcenterservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "laboratory", schema = "research_center")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    private String name;
    private String description;
    private String address;
    @OneToMany(mappedBy = "department")
    private List<Laboratory> laboratories;
}
