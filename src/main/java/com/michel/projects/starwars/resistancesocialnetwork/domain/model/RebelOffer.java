package com.michel.projects.starwars.resistancesocialnetwork.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class RebelOffer {

    private Long rebelId;
    private List<InventoryItem> items;
}
