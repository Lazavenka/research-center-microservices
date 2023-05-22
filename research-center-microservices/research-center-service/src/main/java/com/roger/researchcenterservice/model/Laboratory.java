package com.roger.researchcenterservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "laboratory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Laboratory extends CustomEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(name = "image_file_path")
    private String imageFilePath;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "laboratory")
    private List<Equipment> equipment;
}
