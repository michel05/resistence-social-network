package com.michel.projects.starwars.resistancesocialnetwork.domain.model;

import lombok.Data;

@Data
public class SupplyExchange {

    private RebelOffer requesterOffer;
    private RebelOffer receiverOffer;
}
