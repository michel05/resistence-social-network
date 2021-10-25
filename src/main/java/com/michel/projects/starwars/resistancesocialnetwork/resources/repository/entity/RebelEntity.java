package com.michel.projects.starwars.resistancesocialnetwork.resources.repository.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "rebel")
public class RebelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "betrayer")
    private boolean betrayer;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "betrayer_mark")
    private int betrayerMark;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "rebel_location_id", nullable = false)
    private RebelLocationEntity rebelLocation;

    @OneToMany(mappedBy = "rebel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RebelInventoryItemEntity> rebelInventory;

    public RebelEntity(String name, Integer age, String gender, RebelLocationEntity rebelLocation, List<RebelInventoryItemEntity> rebelInventory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.rebelLocation = rebelLocation;
        this.rebelInventory = rebelInventory;
    }
}
