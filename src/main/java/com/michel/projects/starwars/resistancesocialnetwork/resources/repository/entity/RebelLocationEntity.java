package com.michel.projects.starwars.resistancesocialnetwork.resources.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity(name = "rebel_location")
public class RebelLocationEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Setter
    @Getter
    @Column(name = "latitude", nullable = false)
    private String latitude;
    @Setter
    @Getter
    @Column(name = "longitude", nullable = false)
    private String longitude;
    @Setter
    @Getter
    @Column(name = "war_base_name")
    private String warBaseName;

    public RebelLocationEntity(String latitude, String longitude, String warBaseName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.warBaseName = warBaseName;
    }
}
