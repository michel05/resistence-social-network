package com.michel.projects.starwars.resistancesocialnetwork.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    private List<InventoryItem> items;

    public Optional<InventoryItem> getItemByType(ItemType itemType) {
        return items.stream().filter(it -> it.getItemType().equals(itemType)).findFirst();
    }

    public void exchangeItems(List<InventoryItem> itemsToInsert, List<InventoryItem> itemsToRemove) {
        Arrays.stream(ItemType.values()).forEach(itemType -> {
            var inventoryItem = new InventoryItem(0L, itemType);
            if(!items.contains(inventoryItem)) {
                items.add(inventoryItem);
            }
        });

        itemsToInsert.forEach(toInsert -> {
            var item = items.get(items.indexOf(toInsert));
            item.setQuantity(item.getQuantity() + toInsert.getQuantity());
        });

        itemsToRemove.forEach(toInsert -> {
            var item = items.get(items.indexOf(toInsert));
            item.setQuantity(item.getQuantity() - toInsert.getQuantity());
        });
    }
}
