package com.michel.projects.starwars.resistancesocialnetwork.resources.repository;

import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Inventory;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.InventoryItem;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Rebel;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.RebelLocation;
import com.michel.projects.starwars.resistancesocialnetwork.resources.repository.entity.RebelEntity;
import com.michel.projects.starwars.resistancesocialnetwork.resources.repository.entity.RebelInventoryItemEntity;
import com.michel.projects.starwars.resistancesocialnetwork.resources.repository.entity.RebelLocationEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RebelEntityMapper {

    public RebelEntity mapToEntity(Rebel rebel) {
        var location = new RebelLocationEntity(
                rebel.getLocation().getLatitude(),
                rebel.getLocation().getLongitude(),
                rebel.getLocation().getWarBaseName()
        );

        var items = rebel.getInventory().getItems()
                .stream().map(item ->
                        new RebelInventoryItemEntity(
                                item.getQuantity(),
                                item.getItemType()
                        )).collect(Collectors.toList());
        var entity = new RebelEntity(
                rebel.getName(),
                rebel.getAge(),
                rebel.getGender(),
                location,
                items
        );
        items.forEach(item -> item.setRebel(entity));
        return entity;
    }

    public Rebel mapToModel(RebelEntity rebelEntity) {
        return new Rebel(
                        rebelEntity.getName(),
                        rebelEntity.getGender(),
                        rebelEntity.getAge(),
                        rebelEntity.isBetrayer(),
                new Inventory(rebelEntity.getRebelInventory().stream().map(item ->
                                        new InventoryItem(item.getQuantity(), item.getItemType())
                                ).collect(Collectors.toList())
                        ),
                new RebelLocation(
                                rebelEntity.getRebelLocation().getLatitude(),
                                rebelEntity.getRebelLocation().getLongitude(),
                                rebelEntity.getRebelLocation().getWarBaseName()
                        )
                );
    }

    public RebelEntity mapInventory(RebelEntity rebelEntity,Inventory inventory) {
        rebelEntity.getRebelInventory().forEach(rebelInventoryItemEntity -> {
            var newQuantity = inventory.getItemByType(rebelInventoryItemEntity.getItemType()).get().getQuantity();
            rebelInventoryItemEntity.setQuantity(newQuantity);
        });
        return rebelEntity;
    }
}
