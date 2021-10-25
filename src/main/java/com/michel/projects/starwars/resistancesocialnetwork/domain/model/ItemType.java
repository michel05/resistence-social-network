package com.michel.projects.starwars.resistancesocialnetwork.domain.model;

public enum ItemType {
    FOOD(1),
    AMMO(3),
    WATER(2),
    WEAPON(4);

    private int point;

    ItemType(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
