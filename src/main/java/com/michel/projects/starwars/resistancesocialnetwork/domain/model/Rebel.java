package com.michel.projects.starwars.resistancesocialnetwork.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rebel {

    private final String name;
    private final String gender;
    private int age;
    private boolean betrayer;
    private Inventory inventory;
    private RebelLocation location;
}
