package com.michel.projects.starwars.resistancesocialnetwork.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RebelLocation {
    private String latitude;
    private String longitude;
    private String warBaseName;
}
