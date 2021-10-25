package com.michel.projects.starwars.resistancesocialnetwork.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Data
@AllArgsConstructor
public class InventoryItem {

    @Getter
    @Setter
    private Long quantity;
    @Getter
    private ItemType itemType;

    @JsonIgnore
    public Long getTotalValue() {
        return quantity * itemType.getPoint();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem that = (InventoryItem) o;
        return itemType == that.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType);
    }
}
